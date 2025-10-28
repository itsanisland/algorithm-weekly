import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // Set 변환
        Set<Integer> reserveSet = Arrays.stream(reserve)
            .boxed()
            .collect(Collectors.toSet());
        
        Set<Integer> lostSet = Arrays.stream(lost)
            .boxed()
            .collect(Collectors.toSet());
        
        // 교집합
        Set<Integer> common = new HashSet<>(lostSet);
        common.retainAll(reserveSet);
        
        reserveSet.removeAll(common);
        lostSet.removeAll(common);
        
        int answer = n - lostSet.size(); // 진짜 잃어버린 학생 수

        // 앞, 뒤 확인
        for (int student : lostSet) {
            if (reserveSet.contains(student - 1)) {
                reserveSet.remove(student - 1);
                answer++;
            } else if (reserveSet.contains(student + 1)) {
                reserveSet.remove(student + 1);
                answer++;  
            }  
        }
        
        return answer;
    }
}