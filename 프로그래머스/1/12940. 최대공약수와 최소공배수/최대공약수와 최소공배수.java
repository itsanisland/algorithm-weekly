class Solution {
    
    private int getGCD(int n, int m) {
        return m > 0 ? getGCD(m, n % m) : n;
    }
    
    private int getLCM(int n, int m) {
        return (n * m) / getGCD(n, m);
    }
    
    public int[] solution(int n, int m) {
        int[] answer = {getGCD(n, m), getLCM(n, m)};
        return answer;
    }
}