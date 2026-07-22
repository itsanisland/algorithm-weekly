class Solution {
    
    public int solution(String message, int[][] spoiler_ranges) {
        String[] words = message.split(" ");
        StringBuilder sb = new StringBuilder(message);
        
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                if (sb.charAt(i) == ' ') {
                    continue;
                }
                sb.setCharAt(i, '*');
            }
        }
        
        String[] spoilerWords = sb.toString().split(" ");
        int answer = 0;
        
        for (int i = 0; i < words.length; i++) {
            boolean ck = true;
            if (spoilerWords[i].contains("*")) {
                for (int j = 0; j < words.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    ck = ck && !words[i].equals(spoilerWords[j]);
                }
                if (ck) {
                    answer++;
                }
                spoilerWords[i] = words[i];
            }
        }
        
        return answer;
    }
}