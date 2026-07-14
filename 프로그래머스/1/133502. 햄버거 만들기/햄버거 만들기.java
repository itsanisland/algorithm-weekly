import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        int[] hamburger = {1, 3, 2, 1};
        Stack<Integer> stack = new Stack<>();
        
        for (int i : ingredient) {
            stack.push(i);
            if (stack.size() >= 4) {
                boolean ck = true;
                for (int j = 0; j < 4; j++) {
                    int last = stack.pop();
                    if (last != hamburger[j]) {
                        stack.push(last);
                        for (int k = j - 1; k >= 0; k--) {
                            stack.push(hamburger[k]);
                        }
                        ck = false;
                        break;
                    }
                }
                if (ck) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}