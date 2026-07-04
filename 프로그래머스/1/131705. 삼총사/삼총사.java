class Solution {
    
    private int MAX_N = 13;
    private int n, r = 3, ans;
    private int[] arr;
    private boolean[] selected = new boolean[MAX_N];
    
    private void comb(int start, int cnt, int sum) {
        if (cnt == r) {
            if (sum == 0) {
                ans += 1;
            }
            return;
        }
        
        for (int i = start; i < n; i++) {
            selected[i] = true;
            comb(i + 1, cnt + 1, sum + arr[i]);
            selected[i] = false;
        }
    }
    
    public int solution(int[] number) {
        n = number.length;
        arr = number;
        comb(0, 0, 0);
        return ans;
    }
}