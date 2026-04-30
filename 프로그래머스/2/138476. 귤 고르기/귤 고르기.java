import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        // Map 정렬 (값 기준)
        
        List<Integer> keys = new ArrayList(map.keySet());
        Collections.sort(keys, (a, b) -> {
            return map.get(b) - map.get(a);
        });
        
        int answer = 0;
        
        for (int key : keys) {
            if (k <= 0) {
                break;
            }
            
            k -= map.get(key);
            answer++;
        }
        
        return answer;
    }
}