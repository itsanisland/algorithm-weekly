import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {        
        Arrays.sort(phone_book);
        
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].contains(phone_book[i]) && phone_book[i + 1].charAt(0) == phone_book[i].charAt(0)) {
                return false;
            }
        }
        
        return true;
    }
}