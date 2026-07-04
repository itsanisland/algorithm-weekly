class Solution {
    
    private int toDecimal(String num, int b) {
        int decimal = 0;
        for (char c : num.toCharArray()) {
            decimal *= b;
            decimal += c - '0';
        }
        return decimal;
    }
    
    private String toReversedBinary(int num, int b) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % b);
            num /= 3;
        }
        return sb.toString();
    }
    
    public int solution(int n) {
        String num = toReversedBinary(n, 3);
        int answer = toDecimal(num, 3);
        return answer;
    }
}