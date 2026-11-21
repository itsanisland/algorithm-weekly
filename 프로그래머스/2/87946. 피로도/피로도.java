class Solution {
    
    private int N, answer;
    private int[][] dungeons;
    
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        this.dungeons = dungeons;
        
        // 던전을 방문하는 순서? -> DFS/BFS(n <= 8)
        // 방문하면서 최대 방문 수 갱신
        dfs(k, 0, new boolean[N]);
        
        return answer;
    }
    
    public void dfs(int k, int depth, boolean[] visited) {
        if (depth == N) {
            return;
        }
        
        // 던전을 방문하는 모든 순서를 탐색 -> O(8!) = 40,320
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            
            if (k >= dungeons[i][0]) {
                answer = Math.max(answer, depth + 1);
                visited[i] = true;
                dfs(k - dungeons[i][1], depth + 1, visited);
                visited[i] = false;
            }
        }
        
    }
    
}