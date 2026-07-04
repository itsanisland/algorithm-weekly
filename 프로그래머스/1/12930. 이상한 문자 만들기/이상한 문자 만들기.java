class Solution {
    
    private char toUpper(char c) {
        return 'a' <= c && c <= 'z' ? (char) (c - 'a' + 'A') : c;
    }
    
    private char toLower(char c) {
        return 'A' <= c && c <= 'Z' ? (char) (c - 'A' + 'a') : c;
    }
    
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == ' ') {
                sb.append(' ');
                idx = 0;
            } else {
                sb.append(idx % 2 == 0 ? toUpper(c) : toLower(c));
                idx += 1;
            }
        }
        
        return sb.toString();
    }
}