import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int d = y - x; // 총 이동 거리
            long k = (long) Math.sqrt(d); // 최대 속도
            long ans = 0; // 최소 이동 횟수
            
            if (d == k * k) ans = 2 * k - 1;
            else if (k * k < d && d <= k * k + k) ans = 2 * k;
            else ans = 2 * k + 1;

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}