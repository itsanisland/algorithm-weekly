import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;

        for (int[] cur : info) {
            int aInc = cur[0];
            int bInc = cur[1];

            boolean[][] next = new boolean[n][m];

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (!dp[a][b]) continue;

                    // A팀이 맡음
                    if (a + aInc < n) {
                        next[a + aInc][b] = true;
                    }

                    // B팀이 맡음
                    if (b + bInc < m) {
                        next[a][b + bInc] = true;
                    }
                }
            }

            dp = next;
        }

        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[a][b]) return a;
            }
        }
        
        return -1;
    }
}
