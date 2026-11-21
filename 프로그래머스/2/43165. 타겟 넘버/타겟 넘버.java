class Solution {
    
    private int N, target, answer;
    private int[] numbers;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        this.target = target;
        this.numbers = numbers;
        
        dfs(0, 0);
        
        return answer;
    }
    
    private void dfs(int n, int result) {
        if (n == N) {
            if (target == result) {
                answer += 1;
            }
            return;
        }
        
        dfs(n + 1, result + numbers[n]);
        dfs(n + 1, result - numbers[n]);
    }
}