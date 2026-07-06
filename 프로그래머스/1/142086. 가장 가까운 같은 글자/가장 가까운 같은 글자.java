import java.util.*;

class Solution {
    public int[] solution(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int[] answer = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            answer[i] = i - map.getOrDefault(c, i + 1);
            map.put(c, i);
        }
        
        
        return answer;
    }
}