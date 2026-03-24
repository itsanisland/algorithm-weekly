import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long ans = 0;
            int i = N - 1;

            while (i >= 0) {
                int price = arr[i];
                int cnt = 0, buy = 0;
                i--;
                while (i >= 0 && arr[i] < price) {
                    buy += arr[i];
                    cnt++;
                    i--;
                }

                ans += price * cnt - buy;
            }

            sb.append(ans).append("\n");
        }

        
        System.out.println(sb);
    }
}