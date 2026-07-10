class Solution {
    public int solution(String s) {
        int answer = 1;
        int sameCnt = 0;
        int diffCnt = 0;
        int x = s.charAt(0);
        int i = 0;
        
        while (i < s.length()) {
            char next = s.charAt(i);
            if (next == x) {
                sameCnt++;  
            } else {
                diffCnt++;
            }
            
            i++;
            
            if (sameCnt == diffCnt && i < s.length()) {
                System.out.println(i);
                x = s.charAt(i);
                sameCnt = 0;
                diffCnt = 0;
                answer++;
            }
        } 
        
        return answer;
    }
}