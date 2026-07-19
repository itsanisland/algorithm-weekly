import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        int[][] fromTo = new int[n][n];
        int[] g = new int[n];
        Map<String, Integer> fMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            fMap.put(friends[i], i);
        }
        
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            int from = fMap.get(split[0]);
            int to = fMap.get(split[1]);
            fromTo[from][to]++;
            g[from]++;
            g[to]--;
        }
        
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (fromTo[i][j] > fromTo[j][i]) {
                    cnt++;
                } else if (fromTo[i][j] == fromTo[j][i]) {
                    if (g[i] > g[j]) {
                        cnt++;
                    }
                }
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }
}