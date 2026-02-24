import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] multiples = new boolean[N + 1];
        int ans = 0;

        for (int p = 2; p <= N; p++) {
            for (int m = p; m <= N; m += p) {
                if (!multiples[m]) {
                    multiples[m] = true;
                    if (--K == 0) {
                        ans = m;
                        break;
                    }
                }
            }
            
            if (K == 0) break;
        }

        System.out.println(ans);
    }
}