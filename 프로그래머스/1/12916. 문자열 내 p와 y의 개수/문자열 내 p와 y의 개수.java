class Solution {
    boolean solution(String s) {
        int pCnt = 0, yCnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'p' || ch == 'P') {
                pCnt++;
            }
            
            if (ch == 'y' || ch == 'Y') {
                yCnt++;
            }
        }

        return pCnt == yCnt;
    }
}