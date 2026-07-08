class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int wMax = Math.max(wallet[0], wallet[1]);
        int wMin = Math.min(wallet[0], wallet[1]);
        
        while (Math.max(bill[0], bill[1]) > wMax || Math.min(bill[0], bill[1]) > wMin) {
            bill[bill[0] < bill[1] ? 1 : 0] /= 2;
            answer += 1;
        }
        
        return answer;
    }
}