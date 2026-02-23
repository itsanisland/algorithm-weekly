import java.util.*;
import java.io.*;

class Main {

    static int getGCD(int a, int b) {
        return a % b == 0 ? b : getGCD(b, a % b);
    }

    static int getLCM(int a, int b) {
        return a * b / getGCD(a, b);
    }
    
    static int solve(int M, int N, int x, int y) {
        int lcm = getLCM(M, N);
        for (int i = x; i < lcm; i += M) {
            if (i % N == y) return i + 1;
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            
            int ans = 0;
            if (M < N) ans = solve(N, M, y, x);
            else ans = solve(M, N, x, y);
            System.out.println(ans);
        }
    }
}