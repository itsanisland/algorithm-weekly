class Solution {
    
    int N, M, ans = 120;
    int[][] infos;
    boolean[][][] visited;
    
    public void dfs(int cnt, int size, int a, int b) {
        if (a >= N || b >= M || a >= ans) return;
        if (visited[cnt][a][b]) return;
        
        visited[cnt][a][b] = true;
        
        if (cnt == size) {
            ans = Math.min(ans, a);
            return;
        }

        dfs(cnt + 1, size, a + infos[cnt][0], b);
        dfs(cnt + 1, size, a, b + infos[cnt][1]);
    }
    
    public int solution(int[][] info, int n, int m) {
        infos = info; N = n; M = m;
        int cnt = info.length;
        visited = new boolean[info.length + 1][n][m];
        dfs(0, cnt, 0, 0);
        return ans == 120 ? - 1 : ans;
    }
}