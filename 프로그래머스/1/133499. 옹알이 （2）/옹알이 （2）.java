class Solution {
    public int solution(String[] babbling) {
        String[] words = {"aya", "ye", "woo", "ma"};
        int answer = 0;
        
        for (String s : babbling) {
            if (s.contains("ayaaya") || s.contains("yeye") || s.contains("woowoo") || s.contains("mama")) {
                continue;
            }
            
            for (String word : words) {
                s = s.replace(word, " ");
            }
            
            if (s.split(" ").length == 0) {
                answer += 1;
            }
        }
        
        return answer;
    }
}