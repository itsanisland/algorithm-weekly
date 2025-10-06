import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportingUser = new HashMap<>();
        
        for (String str : report) {
            String[] split = str.split(" ");
            String from = split[0], to = split[1];
            
            Set<String> set = reportingUser.getOrDefault(to, new HashSet<String>());
            if (set.contains(from)) continue;
            
            set.add(from);
            reportingUser.put(to, set);
        }
        
        Map<String, Integer> mailedUser = new HashMap<>();
        
        for (String reportedUser : reportingUser.keySet()) {
            if (reportingUser.get(reportedUser).size() >= k) {
                for (String user : reportingUser.get(reportedUser)) {
                    mailedUser.put(user, mailedUser.getOrDefault(user, 0) + 1);
                }             
            }
        }
        
        int[] answer = new int[id_list.length];
        
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = mailedUser.getOrDefault(id_list[i], 0);
        }
        
        return answer;
    }
}