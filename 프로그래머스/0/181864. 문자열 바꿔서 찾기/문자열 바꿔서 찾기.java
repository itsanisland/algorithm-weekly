class Solution {
    public int solution(String myString, String pat) {
        String str = "";
        
        for (char c : myString.toCharArray()) {
            str += c == 'A' ? 'B' : 'A';
        }
        
        int answer = str.contains(pat) ? 1 : 0;
        return answer;
    }
}