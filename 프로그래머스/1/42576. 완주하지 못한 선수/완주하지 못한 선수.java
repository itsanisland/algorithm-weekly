import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        // {선수이름:참여횟수}
        Map<String, Integer> pMap = new HashMap<>();
        
        // 특정 선수의 참여 횟수 세기(동명이인 고려)
        for (String name : participant) {
            pMap.put(name, pMap.getOrDefault(name, 0) + 1);
        }
        
        // 실제 완주한 선수의 횟수 제외
        for (String name : completion) {
            pMap.put(name, pMap.get(name) - 1);
            
            // 완주 완료한 선수는 Map에서 제거
            if (pMap.get(name) == 0) {
                pMap.remove(name);
            }
        }
        
        String answer = "";
        
        // 완주하지 않은 선수 1명만 Map에 남아있음
        for (String key : pMap.keySet()) {
            answer = key;
        }
        
        return answer;
    }
}