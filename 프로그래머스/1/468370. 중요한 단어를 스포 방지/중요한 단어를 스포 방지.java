import java.util.*;

class Solution {
    
    public int solution(String message, int[][] spoiler_ranges) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder(message);
        int answer = 0;
        
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                if (sb.charAt(i) == ' ') {
                    continue;
                }
                sb.setCharAt(i, '*');
            }
        }
        
        for (String s : sb.toString().split(" ")) {
            set.add(s);
        }

        for (String s : message.split(" ")) {
            if (!set.contains(s)) {
                answer++;
                set.add(s);
            }
        }
        
        return answer;
    }
}