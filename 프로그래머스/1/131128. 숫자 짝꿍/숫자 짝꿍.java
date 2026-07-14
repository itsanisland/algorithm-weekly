import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        int[] numbersX = new int[10];
        int[] numbersY = new int[10];
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < X.length(); i++) {
            numbersX[X.charAt(i) - '0']++;
        }
        
        for (int i = 0; i < Y.length(); i++) {
            numbersY[Y.charAt(i) - '0']++;
        }
        
        for (int i = 0; i < 10; i++) {
            if (numbersX[i] > 0 && numbersY[i] > 0) {
                for (int j = 0; j < Math.min(numbersX[i], numbersY[i]); j++) {
                    list.add(i);
                }
            }
        }
        
        if (list.isEmpty()) {
            return "-1";
        } else {
            Collections.sort(list, Collections.reverseOrder());
            
            if (list.get(0) == 0) {
                return "0";
            }
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
            }
            return sb.toString();
        }
    }
}