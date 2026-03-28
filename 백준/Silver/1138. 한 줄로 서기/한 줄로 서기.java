import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int left = Integer.parseInt(st.nextToken());
            
            // 핵심: 키가 i인 사람이 들어갈 '빈칸'의 개수를 세며 이동합니다.
            int count = 0;
            for (int j = 0; j < N; j++) {
                // 빈칸 발견!
                if (arr[j] == 0) {
                    // 왼쪽에 있어야 할 키 큰 사람 수(빈칸 수)를 다 채웠다면?
                    if (count == left) {
                        arr[j] = i;
                        break;
                    }
                    count++; // 아직 덜 채웠으면 빈칸 개수 증가
                }
            }
        }

        for (int i = 0; i < N; i++) sb.append(arr[i]).append(" ");
        
        System.out.println(sb);
    }
}