import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        
        // Set 변환
        Set<Integer> reserveSet = IntStream.of(reserve)
            .boxed()
            .collect(Collectors.toSet());
        
        Set<Integer> lostSet = IntStream.of(lost)
            .boxed()
            .collect(Collectors.toSet());
        
        Set<Integer> unionSet = IntStream.of(lost)
            .boxed()
            .collect(Collectors.toSet());
        
        unionSet.retainAll(reserveSet);
        reserveSet.removeAll(unionSet);
        lostSet.removeAll(unionSet);
        
        answer += unionSet.size();

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