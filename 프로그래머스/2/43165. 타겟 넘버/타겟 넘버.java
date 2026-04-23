class Solution {
    
    public int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    public void dfs(int[] numbers, int target, int idx, int value) {
        if (idx == numbers.length) {
            if (value == target) {
                answer++;
            }
            return;
        }
        
        dfs(numbers, target, idx + 1, value + numbers[idx]);
        dfs(numbers, target, idx + 1, value - numbers[idx]);
    }
}