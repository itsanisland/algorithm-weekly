import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] gems) {
        int n = gems.length;
        int m = Arrays.stream(gems)
                        .collect(Collectors.toSet())
                        .size();
        
        int s = 0; // 구간을 줄이는 역할
        int e = 0; // 새로운 보석을 추가하는 역할
        int[] answer = { 0, n - 1 };
        
        Map<String, Integer> map = new HashMap<>(); // 보석의 종류 : 현재 구간에서 해당 보석의 포함 개수
        
        while (e < n) {
            map.put(gems[e], map.getOrDefault(gems[e], 0) + 1); // 새로운 보석 추가(확장)
            
            while (map.size() == m) { // 모든 보석이 포함된 구간
                // 최소 길이 갱신
                if (e - s < answer[1] - answer[0]) {
                    answer[0] = s;
                    answer[1] = e;
                }
                
                // start 이동
                map.put(gems[s], map.get(gems[s]) - 1); // 구간 줄이기(축소)
                if (map.get(gems[s]) == 0) map.remove(gems[s]);
                s++;
            }
            
            e++;
        }
        
        return Arrays.stream(answer).map(i -> i + 1).toArray();
    }
}