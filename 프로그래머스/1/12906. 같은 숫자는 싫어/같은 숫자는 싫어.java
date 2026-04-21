import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        // 슬라이딩 윈도우
        
        List<Integer> list = new ArrayList<>();
        int left = 0, right = 0;
        int n = arr.length;
        
        while (left < n && right < n) {
            if (arr[left] == arr[right]) {
                list.add(arr[left]);
                while (right < n && arr[left] == arr[right]) right++;
            } else {
                left++;
            }
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}