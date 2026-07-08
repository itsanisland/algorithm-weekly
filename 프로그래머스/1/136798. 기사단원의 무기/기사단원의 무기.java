class Solution {
    
    private int getDivisorCount(int num) {
        int cnt = 0;
        for (int i = 1; i * i < num; i++) {
            // 1 4 9 16 25 ...
            if (num % i == 0) {
                cnt += 2;
            }
        }
        
        if (Math.sqrt(num) % 1 == 0) { // 제곱수
            cnt += 1;
        }
        
        return cnt;
    }
    
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        for (int i = 1; i <= number; i++) {
            int divisorCount = getDivisorCount(i);
            answer += divisorCount > limit ? power : divisorCount;
        }
        
        return answer;
    }
}