import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] arr = br.readLine().toCharArray(); // String보다 char 배열이 접근이 빠름

        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i] == 'P') { // 사람을 찾으면
                // 내 위치(i) 기준으로 -K부터 +K까지 탐색
                // 단, 가장 왼쪽부터 먹어야 하므로 j의 시작은 i-K
                for (int j = i - K; j <= i + K; j++) {
                    // 범위를 벗어나는 경우 제외
                    if (j < 0 || j >= N) continue;

                    // 햄버거이고 아직 아무도 안 먹었다면
                    if (arr[j] == 'H' && arr[j] != 'X') {
                        arr[j] = 'X';
                        ans++;
                        break; // 가장 왼쪽 하나만 먹고 다음 사람으로
                    }
                }
            }
        }
        System.out.println(ans);
    }
}