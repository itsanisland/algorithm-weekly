import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int n = commands.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            int start = commands[i][0];
            int end = commands[i][1];
            int k = commands[i][2];
            
            answer[i] = getKthNum(array, start, end, k);
        }
        
        return answer;
    }
    
    public int getKthNum(int[] array, int start, int end, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = start - 1; i < end; i++) {
            list.add(array[i]);
        }
        Collections.sort(list);
        return list.get(k - 1);
    }
}