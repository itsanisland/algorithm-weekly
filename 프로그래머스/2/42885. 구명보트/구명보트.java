import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        // 1 2 5 9
        
        int left = 0, right = people.length - 1;
        
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                answer++;
                left++;
                right--;
            } else {
                right--;
            }
        }
        
        answer += people.length - (answer * 2);
        
        return answer;
    }
}