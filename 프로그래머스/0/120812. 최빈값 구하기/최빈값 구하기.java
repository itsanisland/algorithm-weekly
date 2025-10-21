import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num : array) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        List<Map.Entry<Integer, Integer>> entries = map.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toList());
        
        int temp1 = entries.get(map.size() - 1).getValue();
        int temp2 = map.size() > 1 ? entries.get(map.size() - 2).getValue() : -1;
        
        int answer = temp1 == temp2 ? -1: entries.get(map.size() - 1).getKey();
        return answer;
    }
}