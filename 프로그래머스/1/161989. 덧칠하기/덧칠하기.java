class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int start = 0;
        
        while (start < section.length) {
            int last = section[start] + m ;
            while (start < section.length && section[start] < last) {
                start++;
            }
            answer++;
        }
        
        return answer;
    }
}