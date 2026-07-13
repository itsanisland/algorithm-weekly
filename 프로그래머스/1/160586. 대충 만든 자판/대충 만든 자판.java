import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        Map<Character, Integer> map = new HashMap<>();
        
        for (String s : keymap) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, Math.min(i + 1, map.getOrDefault(c, i + 1)));
            }
        }
        
        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            int cnt = 0;
            for (char c : target.toCharArray()) {
                if (map.get(c) == null) {
                    cnt = -1;
                    break;
                }
                cnt += map.get(c);
            }
            answer[i] = cnt;
        }
        
        return answer;
    }
}