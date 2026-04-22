import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        // 투포인터
        List<Integer> list = new ArrayList<>();
        int left = 0;
        int n = arr.length;
        
        while (left < n) {
            // 1. 현재 숫자를 리스트에 추가 (새로운 그룹의 시작)
            list.add(arr[left]);

            int right = left + 1;
            // 2. 현재 숫자(left)와 같은 숫자가 아닐 때까지 right를 이동
            while (right < n && arr[left] == arr[right]) {
                right++;
            }

            // 3. left를 다음 새로운 숫자가 시작되는 위치(right)로 점프!
            left = right;
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}