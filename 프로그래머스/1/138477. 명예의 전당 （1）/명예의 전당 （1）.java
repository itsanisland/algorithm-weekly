import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int[] best = new int[k];

        Arrays.fill(best, 2000);
        
        int len = Math.min(k, score.length);
        
        for (int i = 0; i < len; i++) {
            best[i] = score[i];
            Arrays.sort(best);
            answer[i] = best[0];
        }
        
        for (int i = k; i < score.length; i++) {
            for (int j = k - 1; j >= 0; j--) {
                if (best[j] <= score[i]) {
                    for (int r = 0; r < j; r++) {
                        best[r] = best[r + 1];
                    }
                    best[j] = score[i];
                    break;
                }
            }
            answer[i] = best[0];
        }
        
        return answer;
    }
}