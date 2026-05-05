class Solution {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < new_id.length(); i++) {
            char ch = new_id.charAt(i);
            if ((ch - 'a' >= 0 && ch - 'z' <= 0) ||
                (ch - '0' >= 0 && ch - '9' <= 0) ||
                ch == '-' ||
                ch == '_' ||
                ch == '.') {
                sb.append(ch);
            }
        }

        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) == '.' && sb.charAt(i - 1) == sb.charAt(i)) {
                sb.deleteCharAt(i);
                i--;
            }
        }
        
        // 문자열이 비어있지 않은지 먼저 확인
        if (sb.length() > 0 && sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }

        // 앞의 작업으로 인해 문자열이 비게 될 수 있으므로 다시 한번 길이 확인
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (sb.length() == 0) {
            sb.append("a");
        }
        
        if (sb.length() >= 16) {
            sb.delete(15, sb.length());
            
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        
        if (sb.length() <= 2) {
            char last = sb.charAt(sb.length() - 1);
            while (sb.length() < 3) {
                sb.append(last);
            }
        }
        
        return sb.toString();
    }
}