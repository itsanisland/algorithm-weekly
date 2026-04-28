import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        int n = numbers.length;
        String[] strArr = new String[n];
        
        // 숫자 배열 -> 문자열 배열
        for (int i = 0 ; i < n; i++) {
            strArr[i] = "" + numbers[i];
        }
        
        // 문자열을 비교하여 정렬
        Arrays.sort(strArr, (a, b) -> {
            return (b + a).compareTo(a + b);
        });
        
        // 모든 숫자가 0인 경우 처리
        if (strArr[0].equals("0")) return "0";
        
        for (String str : strArr) {
            answer.append(str);
        }
        
        return answer.toString();
    }
}