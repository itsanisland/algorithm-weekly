import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        int todayDays = convertToDays(today);
        
        for (String t : terms) {
            char type = t.charAt(0);
            int months = Integer.parseInt(t.split(" ")[1]);
            map.put(type, months);
        }
        
        for (int i = 0; i < privacies.length; i++) {
            String[] splits = privacies[i].split(" ");
            String date = splits[0];
            char type = splits[1].charAt(0);
            int dueDateDays = convertToDays(date) + map.get(type) * 28;
            
            if (todayDays >= dueDateDays) {
                list.add(i + 1);
            }
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
    
    // 모든 날짜를 일 단위로 변환하는 헬퍼 함수
    public int convertToDays(String date) {
        String[] splits = date.split("\\.");
        int year = Integer.parseInt(splits[0]);
        int month = Integer.parseInt(splits[1]);
        int day = Integer.parseInt(splits[2]);
        return (year * 12 * 28) + month * 28 + day;
    }
}