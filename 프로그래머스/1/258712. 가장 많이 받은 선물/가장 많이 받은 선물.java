import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        int[][] fromTo = new int[n][n];
        int[] g = new int[n];
        int[] cnt = new int[n];
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
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (fromTo[i][j] > 0 || fromTo[j][i] > 0) {
                    if (fromTo[i][j] > fromTo[j][i]) {
                        cnt[i]++;
                    } else if (fromTo[i][j] < fromTo[j][i]) {
                        cnt[j]++;
                    } else {
                        if (g[i] > g[j]) {
                            cnt[i]++;
                        } else if (g[i] < g[j]) {
                            cnt[j]++;
                        }
                    }
                } else if (fromTo[i][j] == 0 && fromTo[j][i] == 0) {
                    if (g[i] > g[j]) {
                        cnt[i]++;
                    } else if (g[i] < g[j]) {
                        cnt[j]++;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, cnt[i]);
        }
        return answer;
    }
}