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

        // 초기 윈도우
        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) currentType++;
            count[sushi[i]]++;
        }

        int maxType = currentType;
        if (count[c] == 0) maxType++;

        for (int left = 0; left < N; left++) {
            count[sushi[left]]--;
            if (count[sushi[left]] == 0) currentType--;

            int right = (left + k) % N;
            if (count[sushi[right]] == 0) currentType++;
            count[sushi[right]]++;

            maxType = Math.max(maxType, currentType + (count[c] == 0 ? 1 : 0)); 
        }

        System.out.println(maxType);
    }
}