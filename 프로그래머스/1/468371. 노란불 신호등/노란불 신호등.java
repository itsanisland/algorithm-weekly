class Solution {
    
    private int lcm(int a, int b) {
        return b > 0 ? lcm(b, a % b) : a;
    }
    
    private int gcd(int a, int b) {
        return a * b / lcm(a, b);
    }
    
    public int solution(int[][] signals) {
        int n = signals.length;
        int m = signals[0][0] + signals[0][1] + signals[0][2];
        
        for (int i = 1; i < n; i++) {
            m = gcd(m, signals[i][0] + signals[i][1] + signals[i][2]);
        }
        
        m++;
        
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