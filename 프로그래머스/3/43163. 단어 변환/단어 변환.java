import java.util.*;

class Solution {
    
    List<String> W;
    boolean[] visited;
    int min = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        W = List.of(words);
        visited = new boolean[words.length];
        
        if (W.contains(target)) {
            dfs(begin, target, 0);  
            return min;
        } else return 0;
    }
    
    private void dfs(String word, String target, int cnt) {
        if (cnt >= min) return;
        
        if (word.equals(target)) {
            min = cnt;
            return;
        }
        
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < W.size(); j++) {
                int tmp = 0;
                
                for (int k = 0; k < word.length(); k++) {
                    if (i == k) continue;
                    if (word.charAt(k) == W.get(j).charAt(k)) tmp++;
                }
                
                if (tmp == word.length() - 1 && !visited[j]) {
                    visited[j] = true;
                    dfs(W.get(j), target, cnt + 1);
                    visited[j] = false;
                }
            }
        }
    }
}