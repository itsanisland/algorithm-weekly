class Solution {
    
    private int lcm(int a, int b) {
        return b > 0 ? lcm(b, a % b) : a;
    }
    
    private int gcd(int a, int b) {
        return a * b / lcm(a, b);
    }
    
    private boolean isYellow(int sec, int[] signal) {
        sec %= signal[0] + signal[1] + signal[2];
        return sec >= signal[0] + 1 && sec <= signal[0] + signal[1];
    }
    
    public int solution(int[][] signals) {
        int n = signals.length;
        int m = signals[0][0] + signals[0][1] + signals[0][2];
        
        for (int i = 1; i < n; i++) {
            m = gcd(m, signals[i][0] + signals[i][1] + signals[i][2]);
        }
        
        int sec = 1;
        
        while (sec <= m) {
            boolean ck = true;
            for (int i = 0; i < n; i++) {
                ck = ck && isYellow(sec, signals[i]);
            }
            if (ck) {
                return sec;
            }
            sec++;
        }
        
        return -1;
    }
}