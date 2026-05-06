import java.util.*;

class Solution {
    public int solution(String s) {
        String answer = "";
        List<String> nums = new ArrayList();
        nums.add("zero");
        nums.add("one");
        nums.add("two");
        nums.add("three");
        nums.add("four");
        nums.add("five");
        nums.add("six");
        nums.add("seven");
        nums.add("eight");
        nums.add("nine");
        
        String word = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch - '0' >= 0 && ch - '9' <= 0) {
                answer += ch;
                continue;
            }
            
            word += ch;
            if (nums.contains(word)) {
                answer += nums.indexOf(word);
                word = "";
            }
        }
        
        return Integer.parseInt(answer);
    }
}