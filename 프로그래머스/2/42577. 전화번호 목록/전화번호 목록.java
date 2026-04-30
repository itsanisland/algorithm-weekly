import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        // 1. 모든 전화번호를 해시맵에 담습니다. (조회 속도 O(1))
        Map<String, Integer> map = new HashMap<>();
        for (String number : phone_book) {
            map.put(number, 1);
        }

        // 2. 다시 모든 번호를 순회하며 각 번호의 접두어가 맵에 있는지 확인합니다.
        for (String number : phone_book) {
            for (int j = 1; j < number.length(); j++) {
                // 번호의 앞부분을 한 글자씩 늘려가며 잘라봅니다.
                String prefix = number.substring(0, j);
                
                // 자른 앞부분이 맵에 존재한다면, 누군가의 접두어라는 뜻!
                if (map.containsKey(prefix)) {
                    return false;
                }
            }
        }

        return true;
    }
}