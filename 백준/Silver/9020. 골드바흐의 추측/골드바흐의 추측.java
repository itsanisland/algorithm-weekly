import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        boolean[] eratos = new boolean[10_001];
        eratos[0] = eratos[1] = true;

        for (int i = 2; i * i <= 10_000; i++) {
            if (eratos[i]) continue;
            for (int j = i * i; j <= 10_000; j += i) {
                eratos[j] = true;
            }
        }

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int a = 1, b = n;

            for (int i = 2; i <= n / 2; i++) {
                if (eratos[i] || eratos[n - i]) continue;                
                if (Math.abs(i - (n - i)) < Math.abs(a - b)) {
                    a = i; b = n - i;
                }
            }
            
            sb.append(Math.min(a, b) + " " + Math.max(a, b)).append("\n");
        }

        System.out.println(sb);
    }
}