import java.util.*;

class Solution {
    
    int N = 0;
    int[][] visited;
    List<String> map = new ArrayList<>();
    String[] answer;
        
    public String[] solution(String[][] tickets) {
        N = tickets.length + 1;

        visited = new int[N][N];
        answer = new String[N];

        for (int i = 0; i < tickets.length; i++) {
            String to = tickets[i][0];
            String from = tickets[i][1];
            
            if (!map.contains(to)) {
                map.add(to);
            }
            if (!map.contains(from)) {
                map.add(from);
            }
            
            visited[map.indexOf(to)][map.indexOf(from)]++;
        }
        
        dfs("ICN", new String[N], 0);
            
        return answer;
    }
    
    private void dfs(String cur, String[] path, int len) {
        path[len] = cur;
        
        if (len == N - 1) {
            if (answer[0] == null) {
                answer = path.clone();
                return;
            }
            
            for (int i = 0; i < N; i++) {
                if (path[i].compareTo(answer[i]) > 0) break;
                else if (path[i].compareTo(answer[i]) < 0) {
                    answer = path.clone();
                    break;
                }
            }
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (visited[map.indexOf(cur)][i] > 0) {
                visited[map.indexOf(cur)][i]--;
                dfs(map.get(i), path, len + 1);
                visited[map.indexOf(cur)][i]++;
            }
        }
    }
}