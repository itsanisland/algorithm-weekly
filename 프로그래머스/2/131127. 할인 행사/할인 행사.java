import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < 10; i++) {
            String item = discount[i];
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        
        boolean check = true;
        for (int j = 0; j < want.length; j++) {
            String item = want[j];
            if (map.getOrDefault(item, 0) != number[j]) {
                check = false;
                break;
            }
        }

        if (check) {
            answer += 1;
        }
        
        int prev = 0;
        
        for (int i = 10; i < discount.length; i++) {
            String addedItem = discount[i];
            map.put(addedItem, map.getOrDefault(addedItem, 0) + 1);
            
            String removedItem = discount[prev++];
            map.put(removedItem, map.get(removedItem) - 1);
            
            check = true;
            for (int j = 0; j < want.length; j++) {
                String item = want[j];
                if (map.getOrDefault(item, 0) != number[j]) {
                    check = false;
                    break;
                }
            }
            
            if (check) {
                answer += 1;
            }
        }
        
        return answer;
    }
}