import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[][] students = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        int[] scores = {0, 0 , 0};
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < answers.length; j++) {
                if (students[i][j % students[i].length] == answers[j]) {
                    scores[i]++;
                }
            }
        }
                
        int max = 0;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, scores[i]);
        }
        
        List<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            if (scores[i] == max) {
                answer.add(i + 1);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}