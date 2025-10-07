import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private static final int END_OF_DAY = 23 * 60 + 59;
    
    public int[] solution(int[] fees, String[] records) {
        // 차량번호 : 입차시간(분)
        Map<String, Integer> in = new HashMap<>();
        // 차량번호 : 누적주차시간(분)
        // 정렬이 필요하므로 TreeMap
        Map<String, Integer> total = new TreeMap<>();
        
        for (String record : records) {
            // "HH:mm CAR IN/OUT"
            String[] splits = record.split(" ");
            
            int time = toMin(splits[0]);
            String carNum = splits[1];
            boolean out = splits[2].charAt(0) == 'O'; // 'OUT'의 'O' 체크
            
            if (out) {
                int inTime = in.remove(carNum);
                total.put(carNum, total.getOrDefault(carNum, 0) + time - inTime);
            } else {
                in.put(carNum, time);
            }
        }
        
        // 아직 출차 안 한 차량은 23:59로 정산
        for (Map.Entry<String, Integer> entry : in.entrySet()) {
            String carNum = entry.getKey();
            int inTime = entry.getValue();
            total.put(carNum, total.getOrDefault(carNum, 0) + END_OF_DAY - inTime);
        }
        
        // 요금 계산
        List<Integer> answer = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entrySet : total.entrySet()) {
            String carNum = entrySet.getKey();
            float min = (float) entrySet.getValue();
            int totalFee = fees[1];
            
            if (min > fees[0]) totalFee += Math.ceil((min - fees[0]) / fees[2]) * fees[3];
            
            answer.add(totalFee);
        }

        // List -> Primitive 타입 배열
        return answer.stream().mapToInt(i->i).toArray();
    }
    
    private int toMin(String hhmm) {
        // "HH:mm" -> 분
        int h = (hhmm.charAt(0) - '0') * 10 + (hhmm.charAt(1) - '0');
        int m = (hhmm.charAt(3) - '0') * 10 + (hhmm.charAt(4) - '0');
        return h * 60 + m;
    }
}