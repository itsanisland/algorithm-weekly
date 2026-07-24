class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        String[] splits = s.split(" ");
        
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (!temp.isEmpty()) {
                    String str = temp.toString().toLowerCase();
                    char first = Character.toUpperCase(str.charAt(0));
                    answer.append(first + str.substring(1, str.length()));
                }

                temp = new StringBuilder();
                answer.append(' ');
                continue;
            }
            
            temp.append(c);
        }
        
        if (!temp.isEmpty()) {
            String str = temp.toString().toLowerCase();
            char first = Character.toUpperCase(str.charAt(0));
            answer.append(first + str.substring(1, str.length()));
        }
        
        return answer.toString();
    }
}