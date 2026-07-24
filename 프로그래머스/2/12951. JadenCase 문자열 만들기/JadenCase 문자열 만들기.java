class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        boolean ck = true;
        
        for (String str : s.split("")) {
            answer.append(ck ? str.toUpperCase() : str.toLowerCase());
            ck = str.equals(" ");
        }

        return answer.toString();
    }
}