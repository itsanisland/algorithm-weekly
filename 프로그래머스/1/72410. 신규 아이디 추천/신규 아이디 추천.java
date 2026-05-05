class Solution {
    public String solution(String new_id) {
        String str = new_id.toLowerCase();
        
        str = str.replaceAll("[^a-z0-9-_.]", "");
        // [^]: 대괄호 안에 있는 문자들을 제외한 나머지 전부

        str = str.replaceAll("[.]{2,}", ".");
        // {2,}: 2번 이상 반복되는 경우
        
        str = str.replaceAll("^[.]|[.]$", "");
        // ^[.]: 문자열의 시작 (.만 쓰면 모든 문자 의미)
        // |: OR 연산자 -> 둘 중 하나라도 만족하면 해당 부분 선택
        // [.]$: 문자열의 끝
        
        if (str.isEmpty()) {
            str = "a";
        }

        if (str.length() >= 16) {
            str = str.substring(0, 15);
            str = str.replaceAll("[.]$", "");
        }
        
        if (str.length() <= 2) {
            char last = str.charAt(str. length() - 1);
            while (str.length() < 3) {
                str += last;
            }
        }
        
        return str;
    }
}