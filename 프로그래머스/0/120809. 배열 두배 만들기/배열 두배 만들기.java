import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = Arrays.stream(numbers)
            .map(num -> num * 2)
            .toArray();
        return answer;
    }
}