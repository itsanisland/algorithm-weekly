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

            for (int i = n / 2; i >= 2; i--) { // 가운데부터 시작
                if (eratos[i] || eratos[n - i]) continue;                
                sb.append(i + " " + (n - i)).append("\n");
                break;
            }
        }

        System.out.println(sb);
    }
}