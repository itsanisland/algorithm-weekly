class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        boolean[] isLost = new boolean[n];
        boolean[] canBorrow = new boolean[n];
        
        for (int student : lost) {
            isLost[student - 1] = true;
        }
        
        for (int student : reserve) {
            if (isLost[student - 1]) {
                isLost[student - 1] = false;
            } else {
                canBorrow[student - 1] = true;
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            if (!isLost[i]) {
                answer++;
                continue;
            }
            
            if (i + 1 < n && canBorrow[i + 1]) {
                answer++;
                canBorrow[i + 1] = false;
            } else if (i - 1 >= 0 && canBorrow[i - 1]) {
                answer++;
                canBorrow[i - 1] = false;
            }
        }
        
        return answer;
    }
}