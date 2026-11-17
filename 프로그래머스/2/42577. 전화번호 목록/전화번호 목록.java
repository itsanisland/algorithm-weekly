import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        
        // 정렬하면 접두어인 관계는 바로 옆에 붙음
        for (int i = 0; i < phone_book.length - 1; i++) {
            // 인접한 것만 비교
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        
        return true;
    }
}