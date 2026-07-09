import java.util.*;

class Solution {
    public int solution(int n) {
        boolean[] check = new boolean[n + 1];
        Arrays.fill(check, true);
        
        for (int i = 2; i <= n; i++) {
            for (int j = 2; i * j <= n; j++) {
                check[i * j] = false;
            }
        }
        
        int answer = 0;
        
        for (int i = 2; i <= n; i++) {
            answer += check[i] ? 1 : 0;
        }
         
        return answer;
    }
}