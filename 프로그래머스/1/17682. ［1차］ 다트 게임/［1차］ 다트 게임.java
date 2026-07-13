import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < dartResult.length(); i++) {
            char first = dartResult.charAt(i);
            
            if (first == '*') {
                for (int j = 0; j < 2; j++) {
                    if (list.size() - 1 - j < 0) break;
                    int prev = list.get(list.size() - 1 - j);
                    list.set(list.size() - 1 - j, prev * 2);
                }
            } else if (first == '#') {
                list.set(list.size() - 1, -list.get(list.size() - 1));
            } else {
                int score = first - '0';
                if (first == '1' && dartResult.charAt(i + 1) == '0') {
                    score = 10;
                    i++;
                } 
                char part = dartResult.charAt(++i);
                if (part == 'D') {
                    score = score * score;
                }
                if (part == 'T') {
                    score = score * score * score;
                }
                list.add(score);
            }
        }
        
        for (int score : list) {
            answer += score;
        }
        
        return answer;
    }
}