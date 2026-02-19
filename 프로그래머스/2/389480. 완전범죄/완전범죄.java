import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        final int INF = Integer.MAX_VALUE;
        
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int[] cur : info) {
            int aInc = cur[0];
            int bInc = cur[1];

            int[] next = new int[m];
            Arrays.fill(next, INF);

            for (int b = 0; b < m; b++) {
                if (dp[b] == INF) continue;

                // A가 맡음
                int newA = dp[b] + aInc;
                if (newA < n) {
                    next[b] = Math.min(next[b], newA);
                }

                // B가 맡음
                int newB = b + bInc;
                if (newB < m) {
                    next[newB] = Math.min(next[newB], dp[b]);
                }
            }

            dp = next;
        }

        int ans = INF;
        for (int b = 0; b < m; b++) {
            ans = Math.min(ans, dp[b]);
        }

        return ans == INF ? -1 : ans;
    }
}
