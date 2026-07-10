class Solution {
    
    private boolean isPrime(int n) {
        if (n == 1) {
            return false;
        } else {
            for (int i = 2; i * i <= n; i++) {
                if (n %  i == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int comb(int[] nums, int selected, int start, int sum) {
        if (selected == 3) {
            return isPrime(sum) ? 1: 0;
        }
        
        int cnt = 0;
        for (int i = start; i < nums.length; i++) {
            cnt += comb(nums, selected + 1, i + 1, sum + nums[i]);
        }
        return cnt;
    }
    
    public int solution(int[] nums) {
        return comb(nums, 0, 0, 0);
    }
}