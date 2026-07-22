class Solution {
    
    public int solution(String message, int[][] spoiler_ranges) {
        String[] words = message.split(" ");

        StringBuilder sb = new StringBuilder(message);
        
        for (int[] range : spoiler_ranges) {
            int start = range[0];
            int end = range[1];
            for (int i = start; i <= end; i++) {
                if (sb.charAt(i) == ' ') {
                    continue;
                }
                sb.setCharAt(i, '*');
            }
        }
        
        message = sb.toString();
        String[] spoilerWords = message.split(" ");
        int answer = 0;
        
        for (int i = 0; i < words.length; i++) {
            boolean ck = true;
            if (spoilerWords[i].contains("*")) {
                for (int j = 0; j < words.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (words[i].equals(spoilerWords[j])) {
                        ck = false;
                        break;
                    }
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