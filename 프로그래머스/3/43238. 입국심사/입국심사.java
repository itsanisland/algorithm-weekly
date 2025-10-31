import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long max = 0;
        for (int time : times) max = Math.max(max, time);
        
        long s = 0, e = n * max;
        
        long answer = e;
        
        while (s <= e) {
            long mid = (s + e) / 2; // '이 시간 안에 n명을 다 심사할 수 있을까?'를 실험하는 시간값
            
            long cnt = 0;
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
                
                if (cnt >= n) break;
            }
            
            if (cnt < n) {
                s = mid + 1;
            } else {
                e = mid - 1;
                
                // mid 시간 안에 n명 이상을 심사할 수 있다면
                // 더 짧은 시간으로도 가능할 지 확인해야 한다.
                // 즉 == n 일 때 멈추는 것이 아니라(이진 탐색이 제대로 수렴 X)
                // n 이상 가능하면 왼쪽으로 탐색해야 함!
                answer = mid;
            }
        }

        return answer;
    }
}