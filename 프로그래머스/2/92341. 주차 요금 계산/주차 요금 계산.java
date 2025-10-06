import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> inRecords = new HashMap<>();
        Map<String, Integer> totalMin = new HashMap<>();
        
        for (String record : records) {
            String[] splits = record.split(" ");
            
            String time = splits[0], carNum = splits[1], state = splits[2];
            
            if (state.equals("OUT")) {
                String[] inTime = inRecords.get(carNum).split(":");
                String[] outTime = time.split(":");
                
                int inHour = Integer.parseInt(inTime[0]), inMin = Integer.parseInt(inTime[1]);
                int outHour = Integer.parseInt(outTime[0]), outMin = Integer.parseInt(outTime[1]);
                
                inMin += 60 * inHour;
                outMin += 60 * outHour;
                
                totalMin.put(carNum, totalMin.getOrDefault(carNum, 0) + outMin - inMin);
                inRecords.remove(carNum);
            } else {
                inRecords.put(carNum, time);
            }
        }
        
        for (Map.Entry<String, String> entrySet : inRecords.entrySet()) {
            String carNum = entrySet.getKey();
            String[] inTime = entrySet.getValue().split(":");
            int inHour = Integer.parseInt(inTime[0]), inMin = Integer.parseInt(inTime[1]);
            inMin += 60 * inHour;
            int outMin = 60 * 23 + 59;
            totalMin.put(carNum, totalMin.getOrDefault(carNum, 0) + outMin - inMin);
        }
        
        // totalMin key 기반 정렬
        List<Map.Entry<String, Integer>> entries = totalMin.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toList());
        
        List<Integer> answer = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entrySet : entries) {
            String carNum = entrySet.getKey();
            float min = (float) entrySet.getValue();
            int totalFee = fees[1];
            
            if (min > fees[0]) totalFee += Math.ceil((min - fees[0]) / fees[2]) * fees[3];
            
            answer.add(totalFee);
        }

        return answer.stream().mapToInt(i->i).toArray();
    }
}