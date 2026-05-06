import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; i++) {
            q.offer((100 - progresses[i] + speeds[i] - 1) / speeds[i]);
        }
        
        int cur = q.poll();
        int cnt = 1;
        while (!q.isEmpty()) {
            int day = q.poll();
            if (cur < day) {
                answer.add(cnt);
                cur = day;
                cnt = 1;
            } else {
                cnt++;
            }
        }
        answer.add(cnt);
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}