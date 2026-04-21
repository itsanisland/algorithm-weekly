import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> pMap = new HashMap<>();
        
        for (String name : participant) {
            pMap.put(name, pMap.getOrDefault(name, 0) + 1);
        }
        
        for (String name : completion) {
            pMap.put(name, pMap.get(name) - 1);
            
            if (pMap.get(name) == 0) {
                pMap.remove(name);
            }
        }
        
        String answer = "";
        
        for (String key : pMap.keySet()) {
            answer = key;
        }
        
        return answer;
    }
}