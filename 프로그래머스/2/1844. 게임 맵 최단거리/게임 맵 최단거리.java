import java.util.*;

class Solution {
    
    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {-1, 1, 0, 0};
    
    private int N, M;
    private int[][] maps;
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        this.maps = maps;
        
        return bfs();
    }
    
    private boolean isValid(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
    
    private int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 1});
        
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0], cx = cur[1], cnt = cur[2];
            
            if (cy == N - 1 && cx == M - 1) {
                return cnt;
            }
            
            for (int d = 0; d < 4; d++) {
                int ny = cy + DY[d];
                int nx = cx + DX[d];
                
                if (isValid(ny, nx) && maps[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new int[] {ny, nx, cnt + 1});
                }
            }
        }
        
        return -1;
    }
}