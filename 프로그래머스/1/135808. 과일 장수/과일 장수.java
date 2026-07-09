import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0; // 얻을 수 있는 최대 이익
        
        Arrays.sort(score);
        
        for (int i = score.length; i >= m; i -= m) {
            int min = score[i - m];
            answer += min * m;
        }
        
        return answer;
    }
}