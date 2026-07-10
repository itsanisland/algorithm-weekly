class Solution {
    public int solution(String s) {
        int answer = 0;
        int sameCnt = 0;
        int diffCnt = 0;
        char x = ' ';
        
        for (char c : s.toCharArray()) {
            if (x == ' ') {
                x = c;
                answer++;
            }
            
            if (c == x) {
                sameCnt++;  
            } else {
                diffCnt++;
            }
            
            if (sameCnt == diffCnt) {
                x = ' ';
                sameCnt = 0;
                diffCnt = 0;
            }
        } 
        
        return answer;
    }
}