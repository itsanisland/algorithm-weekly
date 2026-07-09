class Solution {
    public int solution(String[] babbling) {
        String[] words = {"aya", "ye", "woo", "ma"};
        int answer = 0;
        
        for (String s : babbling) {
            StringBuilder sb = new StringBuilder();
            String prev = "";
            int cnt = 0;
            
            for (int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(i));
                String temp = sb.toString();
                
                for (String word : words) {
                    if (temp.equals(word) && !temp.equals(prev)) {
                        cnt += 1;
                        prev = temp;
                        while (sb.length() > 0) {
                            sb.deleteCharAt(0);
                        }
                    }
                }
            }
            
            if (cnt > 0 && sb.length() == 0) {
                answer += 1;
            }
        }
        
        return answer;
    }
}