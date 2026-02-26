import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int len = 10000000; // sqrt(10^14)
        boolean[] eratos = new boolean[len + 1];
        eratos[0] = eratos[1] = true;
        int ans = 0;
        
        for (int i = 2; i * i <= len; i++) {
            if (eratos[i]) continue;
            
            // 소수의 배수: 소수*N
            for (int j = i * i; j <= len; j += i) {
                eratos[j] = true;
            }
        }

        for (int i = 2; i <= len; i++) {
            if (eratos[i]) continue;

            long temp = (long) i * i;

            // 소수의 거듭제곱: 소수^N (N>=2)
            while (temp <= B) {
                if (temp >= A) ans++;

                // 오버플로우 방지
                // 다음 곱셈이 B를 넘을지, 혹은 long 범위를 넘을지 미리 체크
                // temp * i > B => 그러나 이 식을 그대로 쓰면 오버플로우 발생 가능
                // temp > B / i => 나눠서 비교하기 기법
                if (temp > B / i) break;
                temp *= i;
            }
        }
        
        System.out.println(ans);
    }
}