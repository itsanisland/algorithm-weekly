import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        int n = players.length;
        Map<String, Integer> rankMap = new HashMap<>();
        int idx = 0;
        
        for (String p : players) {
            rankMap.put(p, idx++);
        }
        
        for (String p : callings) {
            int rank = rankMap.get(p);
            String prevP = players[rank - 1];
            rankMap.put(prevP, rankMap.get(prevP) + 1);
            rankMap.put(p, rank - 1);
            players[rank] = prevP;
            players[rank - 1] = p;
        }
        
        // for (Map.Entry<String, Integer> entry : rankMap.getEntry()) {
        //     System.out.println(entry.getKey() + " " + entry.getValue());
        // }
        
        List<String> keys = new ArrayList(rankMap.keySet());
        Collections.sort(keys, (a, b) -> {
           return Integer.compare(rankMap.get(a), rankMap.get(b));
        });
    
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            answer[i] = keys.get(i);
        }
        return answer;
    }
}