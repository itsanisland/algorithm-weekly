import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {
            String s = survey[i];
            char first = s.charAt(0), second = s.charAt(1);
            int c = choices[i];
            if (c < 4) {
                map.put(first, map.getOrDefault(first, 0) + 4 - c);
            }
            if (c > 4) {
                map.put(second, map.getOrDefault(second, 0) + c - 4);
            }
        }
        
        answer += map.getOrDefault('R', 0) < map.getOrDefault('T', 0) ? 'T' : 'R';
        answer += map.getOrDefault('C', 0) < map.getOrDefault('F', 0) ? 'F' : 'C';
        answer += map.getOrDefault('J', 0) < map.getOrDefault('M', 0) ? 'M' : 'J';
        answer += map.getOrDefault('A', 0) < map.getOrDefault('N', 0) ? 'N' : 'A';
        
        return answer;
    }
}