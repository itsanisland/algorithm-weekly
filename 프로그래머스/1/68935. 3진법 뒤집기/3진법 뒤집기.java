class Solution {
    
    private int toBase10(String num, int b) {
        int decimal = 0;
        for (char c : num.toCharArray()) {
            decimal *= b;
            decimal += c - '0';
        }
        return decimal;
    }
    
    private String toReversedBase3(int num, int b) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % b);
            num /= 3;
        }
        return sb.toString();
    }
    
    public int solution(int n) {
        String num = toReversedBase3(n, 3);
        int answer = toBase10(num, 3);
        return answer;
    }
}