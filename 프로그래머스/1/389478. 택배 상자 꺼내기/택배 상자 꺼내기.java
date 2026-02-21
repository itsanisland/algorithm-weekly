import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        Stack<Integer>[] stacks = new Stack[w];
        for (int i = 0; i < w; i++) stacks[i] = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            int idx = i % w;
            int level = i / w;
            if (level % 2 != 0) idx = w - idx - 1;
            stacks[idx].push(i + 1);
        }
        
        int idx = (num - 1) % w;
        int level = (num - 1) / w;
        if (level % 2 != 0) idx = w - idx - 1;
        int answer = 1;
        
        while (stacks[idx].peek() != num) {
            stacks[idx].pop();
            answer++;
        }

        return answer;
    }
}