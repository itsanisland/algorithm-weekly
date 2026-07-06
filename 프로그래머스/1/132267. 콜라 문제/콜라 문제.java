class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        while (n >= a) {
            answer += n / a * b;
            int remains = n % a;    
            n /= a;
            n *= b;
            n += remains;
        }
        
        return answer;
    }
}