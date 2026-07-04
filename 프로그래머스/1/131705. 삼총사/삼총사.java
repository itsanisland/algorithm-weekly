class Solution {
    
    private int MAX_N = 13;
    private int n, r = 3;
    private int[] arr;
    private boolean[] selected = new boolean[MAX_N];
    
    private int comb(int start, int cnt, int sum) {
        if (cnt == r) {
            return sum == 0 ? 1 : 0;
        }
        
        int ans = 0;
        
        for (int i = start; i < n; i++) {
            // selected[i] = true;
            ans += comb(i + 1, cnt + 1, sum + arr[i]);
            // selected[i] = false;
        }
        
        return ans;
    }
    
    public int solution(int[] number) {
        n = number.length;
        arr = number;
        return comb(0, 0, 0);
    }
}