import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            
            for (int i = 2; i * i <= N; i++) {
                int cnt = 0;    
                while (N % i == 0) {
                    N /= i;
                    cnt++;
                }
                if (cnt == 0) continue;
                sb.append(i + " " + cnt).append("\n");                
            }

            // 핵심: for 루프가 완전히 종료된 후, 아직 1보다 큰 N이 남아있다면 
            // 그 값은 N의 가장 큰 마지막 소인수
            if (N > 1) {
                sb.append(N + " " + 1).append("\n");  
            }
        }

        System.out.println(sb);
    }
}