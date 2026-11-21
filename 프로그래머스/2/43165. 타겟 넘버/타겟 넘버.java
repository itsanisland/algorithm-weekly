class Solution {
    
    // 변하지 않는 값은 필드
    private int N, target, answer;
    private int[] numbers;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        this.target = target;
        this.numbers = numbers;
        
        answer = dfs(0, 0);
        
        return answer;
    }
    
    // 바뀌는 값은 파라미터
    private int dfs(int idx, int sum) {
        if (idx == N) {
            return target == sum ? 1 : 0;
        }
        
        return dfs(idx + 1, sum + numbers[idx]) + dfs(idx + 1, sum - numbers[idx]);
    }
}