class Solution {
    
    public int N, answer;
    public int[][] d;
    
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        d = dungeons;
        
        // 던전을 방문하는 순서? -> DFS/BFS(n <= 8)
        // 방문하면서 최대 방문 수 갱신
        dfs(k, 0, 0, new boolean[N]);
        
        return answer;
    }
    
    public void dfs(int k, int start, int depth, boolean[] visited) {
        if (depth == N) {
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            
            if (k >= d[i][0]) {
                answer = Math.max(answer, depth + 1);
                visited[i] = true;
                dfs(k - d[i][1], i + 1, depth + 1, visited);
                visited[i] = false;
            }
        }
        
    }
    
    
}