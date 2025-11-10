import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long N = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());
    
            if (K >= 60) System.out.println(0);  // 2^60 > 1e18 → 항상 0
            else System.out.println(N / (1L << K) - N / (1L << (K + 1)));
        }
    }
}