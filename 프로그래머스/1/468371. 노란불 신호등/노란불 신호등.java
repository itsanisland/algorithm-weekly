class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        int m = 3_200_001;
        boolean[][] isYellow = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            int duration = signals[i][1];
            for (int j = signals[i][0]; j < m; j += signals[i][0] + signals[i][1] + signals[i][2]) {
                for (int k = 1; k <= duration; k++) {
                    if (j + k >= m) {
                        break;
                    }
                    isYellow[i][j + k] = true;
                }
            }
        }
        
        for (int i = 1; i < m - 1; i++) {
            boolean ck = true;
            for (int j = 0; j < n - 1; j++) {
                if (!isYellow[j][i] || !isYellow[j + 1][i]) {
                    ck = false;
                    break;
                }
            }
            if (ck) {
                return i;
            }
        }
        
        return -1;
    }
}