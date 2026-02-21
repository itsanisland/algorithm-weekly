import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int empCnt = schedules.length;
        int emp[] = new int[empCnt];
        startday--;
        
        // 일주일간
        for (int d = 0; d < 7; d++) {
            int day = (startday + d) % 7;
            
            // 주말이면 패스
            if (day >= 5) continue;
            
            // 각 직원 출근 시간 확인
            for (int i = 0; i < empCnt; i++) {
                int schedule = schedules[i];
                int timelog = timelogs[i][d];
                
                if (toMinute(timelog) <= toMinute(schedule + 10)) emp[i]++;
            }
        }
        
        for (int i = 0; i < empCnt; i++) {
            if (emp[i] == 5) answer++;
        }
        
        return answer;
    }
    
    private int toMinute(int time) {
        int h = time / 100;
        int m = time % 100;
        return h * 60 + m;
    }
}