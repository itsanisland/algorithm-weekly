import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int score = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < dartResult.length(); i++) {
            char first = dartResult.charAt(i);
            
            if (Character.isDigit(first)) {
                score = first - '0';
                if (first == '1' && dartResult.charAt(i + 1) == '0') {
                    score = 10;
                    i++;
                } 
            } else {
                char part = dartResult.charAt(i);
                if (part == 'S') {
                    score = score;
                } else if (part == 'D') {
                    score = score * score;
                } else if (part == 'T') {
                    score = score * score * score;
                }

                if (i < dartResult.length() - 1) {
                    char option = dartResult.charAt(i + 1);
                    if (option == '*') {
                        if (!stack.isEmpty()) {
                            stack.push(stack.pop() * 2);
                        }
                        score *= 2;
                        i++;
                    } else if (option == '#') {
                        score *= -1;
                        i++;
                    }
                }
                
                stack.push(score);
            }
        }
        
        for (int s : stack) {
            answer += s;
        }
        
        return answer;
    }
}