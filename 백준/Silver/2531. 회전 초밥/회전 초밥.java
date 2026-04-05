import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) sushi[i] = Integer.parseInt(br.readLine());

        int[] count = new int[d + 1]; // 초밥 종류별 개수 카운트
        int currentType = 0; // 현재 윈도우 내 서로 다른 초밥 종류 수

        // 1. 초기 윈도우 (0 ~ k-1번까지) 설정
        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) currentType++;
            count[sushi[i]]++;
        }

        int maxType = currentType;
        // 쿠폰 처리: 쿠폰 번호가 윈도우에 없으면 종류 +1
        if (count[c] == 0) maxType += 1;

        // 2. 슬라이딩 윈도우 시작 (start는 0부터 N-1까지 이동)
        for (int i = 0; i < N; i++) {
            // 왼쪽(i번) 접시 제거
            count[sushi[i]]--;
            if (count[sushi[i]] == 0) currentType--;

            // 오른쪽(i+k번) 접시 추가 (원형이므로 % N)
            int next = sushi[(i + k) % N];
            if (count[next] == 0) currentType++;
            count[next]++;

            // 쿠폰 포함해서 최댓값 계산
            int totalWithCoupon = currentType;
            if (count[c] == 0) totalWithCoupon++;
            
            maxType = Math.max(maxType, totalWithCoupon);
            
            // 만약 이미 가능한 최대 종류 수(k+1)에 도달했다면 조기 종료 가능
            if (maxType == k + 1) break;
        }

        System.out.println(maxType);
    }
}