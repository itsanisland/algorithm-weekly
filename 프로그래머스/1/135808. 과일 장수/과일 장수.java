import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0; // 얻을 수 있는 최대 이익
        
        Arrays.sort(score);
        
        // 7 -> 3
        for (int i = score.length - 1; i >= 0; i -= m) {
            if (i - m + 1 >= 0) {
                int min = score[i - m + 1];
                answer += min * m;
            }
        }
        
        return answer;
    }
}