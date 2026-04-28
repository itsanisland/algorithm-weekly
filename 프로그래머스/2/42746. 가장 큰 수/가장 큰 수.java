import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        int n = numbers.length;
        String[] strArr = new String[n];
        
        for (int i = 0 ; i < n; i++) {
            strArr[i] = "" + numbers[i];
        }
        
        Arrays.sort(strArr, (a, b) -> {
            return (b + a).compareTo(a + b);
        });
        
        for (String str : strArr) {
            answer += str;
        }
        
        return answer.charAt(0) == '0' ? "0" : answer;
    }
}