class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int wMax = Math.max(wallet[0], wallet[1]);
        int wMin = Math.min(wallet[0], wallet[1]);
        int bMax = Math.max(bill[0], bill[1]);
        int bMin = Math.min(bill[0], bill[1]);
        
        while (bMax > wMax || bMin > wMin) {
            bMax /= 2;
            if (bMax < bMin) {
                int temp = bMax;
                bMax = bMin;
                bMin = temp;
            }
            answer += 1;
        }
        
        return answer;
    }
}