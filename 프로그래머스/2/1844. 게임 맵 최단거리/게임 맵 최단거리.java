import java.util.*;

class Solution {
    
    int[] DY = { 1, -1, 0, 0 };
    int[] DX = { 0, 0, -1, 1};
    int N, M;
    boolean[][] visited;
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        visited = new boolean[N][M];
        
        int answer = bfs(maps);
        return answer;
    }
    
    int bfs(int[][] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { 0, 0, 1 });
        
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], d = cur[2];
            
            if (y == N - 1 && x == M - 1) return d;
            
            for (int i = 0; i < 4; i++) {
                int ny = y + DY[i], nx = x + DX[i];
                
                if (!isValid(ny, nx) || maps[ny][nx] == 0 || visited[ny][nx]) continue;
                
                visited[ny][nx] = true;
                q.offer(new int[] { ny, nx, d + 1 });
            }
        }
        
        return -1;
    }
    
    boolean isValid(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}