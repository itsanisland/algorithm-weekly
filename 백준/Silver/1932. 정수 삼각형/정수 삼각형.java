import java.io.*;
import java.util.*;

class Main {

    public static int n, ans;
    public static int[][] a, dp;

    public static int dfs(int r, int c) {
        // 이미 계산된 상태라면 바로 반환
        if (dp[r][c] != - 1) return dp[r][c];

        if (r == n - 1) return dp[r][c] = a[r][c];

        int down = dfs(r + 1, c);
        int diag = dfs(r + 1, c + 1);

        return dp[r][c] = Math.max(down, diag) + a[r][c];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n][n];
        dp = new int[n][n];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i - 1], -1);
            for (int j = 0; j < i; j++) {
                a[i - 1][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }
}