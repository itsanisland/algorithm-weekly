class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int numer = numer1 * denom2 + numer2 * denom1;
        int denom = denom1 * denom2;
        
        // 분모의 최소공배수 계산
        int lcm = getGCD(numer, denom);
        
        int[] answer = {numer / lcm, denom / lcm};
        
        return answer;
    }
    
    private int getGCD(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}