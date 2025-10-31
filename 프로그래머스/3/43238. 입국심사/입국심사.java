import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long max = Arrays.stream(times)
                .max()
                .getAsInt();
        
        long s = 0, e = n * max;
        
        long answer = max;
        
        while (s <= e) {
            long mid = (s + e) / 2;
            
            long cnt = 0;
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
                
                if (cnt > n) break;
            }
            
            if (cnt < n) {
                s = mid + 1;
            } else {
                e = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }
}