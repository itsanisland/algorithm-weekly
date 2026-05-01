import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        
        for (int t : topping) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        int answer = 0;
        for (int t : topping) {
            set.add(t);
            
            int count = map.get(t) - 1;
            if (count == 0) {
                map.remove(t);
            } else {
                map.put(t, count);
            }
            
            if (set.size() == map.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}