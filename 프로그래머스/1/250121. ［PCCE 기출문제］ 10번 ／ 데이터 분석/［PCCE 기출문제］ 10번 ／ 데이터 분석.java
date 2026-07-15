import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> map = new HashMap<>();
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);
        int extIdx = map.get(ext);
        int sortByIdx = map.get(sort_by);
        List<int[]> list = new ArrayList<>();
        
        for (int i = 0; i < data.length; i++) {
            if (data[i][extIdx] < val_ext) {
                list.add(data[i]);
            }
        }
        
        int[][] answer = new int[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        } 
        
        Arrays.sort(answer, (a, b) -> {
           return a[sortByIdx] - b[sortByIdx];
        });
        
        return answer;
    }
}