import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String myString, String pat) {
        String str = myString.chars()                               // IntStream (각 문자의 유니코드 값)
                    .mapToObj(c -> (char)(c == 'A' ? 'B' : 'A'))    // char 변환
                    .map(String::valueOf)                           // String으로 변환
                    .collect(Collectors.joining());                 // 문자열 합치기
        System.out.println(str);
        int answer = str.contains(pat) ? 1 : 0;
        return answer;
    }
}