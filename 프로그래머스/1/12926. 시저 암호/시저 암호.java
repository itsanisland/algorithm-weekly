class Solution {
    
    private char move(char c, int n) {
        int moved = c + n;
        int last = 'a' <= c && c <= 'z' ? 'z' : 'Z';
        return (char) (moved > last ? moved - 26 : moved);
    }
    
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c == ' ' ? c : move(c, n));
        }
        return sb.toString();
    }
}