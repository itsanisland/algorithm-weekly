import java.util.*;
import java.io.*;

class Main {

    public static void add(int n, int[] c) {
        for (int i = 0; i < n; i++) {
            if (c[i] % 2 != 0) {
                c[i] += 1;
            }
        }
    }

    public static void share(int n, int[] c) {
        int temp = c[n - 1];
        for (int i = n - 1; i > 0; i--) {
            c[i] += c[i - 1] / 2;
            c[i - 1] -= c[i - 1] / 2; 
        }
        c[0] += temp / 2;
        c[n - 1] -= temp / 2;
    }

    public static boolean isSameNums(int n, int[] c) {
        for (int i = 1; i < n; i++) {
            if (c[i - 1] != c[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] c = new int[n];
            for (int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;

            add(n, c);

            while (!isSameNums(n, c)) {
                share(n, c);
                add(n, c);
                ans++;
            }

            System.out.println(ans);
        }
    }
}