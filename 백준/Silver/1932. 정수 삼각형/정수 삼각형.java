import java.io.*;
import java.util.*;

class Main {

    public static int n, ans;
    public static int[][] a, dp;

    // (r, c)에서 시작해서
    // 삼각형 맨 아래까지 내려갔을 때
    // 얻을 수 있는 최대 합
    public static int dfs(int r, int c) {
        // 이미 계산된 상태라면 바로 반환
        if (dp[r][c] != - 1) return dp[r][c];

        // 맨 아래 줄이라면 더 내려갈 수 없으므로 그 칸의 값 자체가 최댓값
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