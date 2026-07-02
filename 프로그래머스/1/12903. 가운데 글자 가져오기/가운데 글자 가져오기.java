class Solution {
    public String solution(String s) {
        int mid = s.length() / 2;
        char ch = s.charAt(mid);
        String answer = "" + ch;
        return (s.length() % 2 == 0 ? s.charAt(mid - 1) : "") + answer;
    }
}