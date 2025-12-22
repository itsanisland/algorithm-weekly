import java.util.*;
import java.io.*;

class Main {

    public static int N, ans;
    public static boolean[] col, diagL, diagR;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        ans = 0;

        col = new boolean[N];
        diagL = new boolean[2 * N]; // r - c
        diagR = new boolean[2 * N]; // r + c

        dfs(0);
        
        System.out.println(ans);
    }

    public static void dfs(int r) {
        if (r == N) {
            ans++;
            return;
        }

        for (int c = 0; c < N; c++) {
            if (col[c] || diagL[r + c] || diagR[r - c + N]) continue;

            col[c] = true;
            diagL[r + c] = true;
            diagR[r - c + N] = true;
            
            dfs(r + 1);

            col[c] = false;
            diagL[r + c] = false;
            diagR[r - c + N] = false;
        }
    }
}