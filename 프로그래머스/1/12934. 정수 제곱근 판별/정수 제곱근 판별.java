import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = -1;
        
        double sqrt = Math.sqrt(n);
        
        if (sqrt == (int) sqrt) {
            answer = (long) Math.pow(sqrt + 1, 2);
        }
        
        return answer;
    }
}