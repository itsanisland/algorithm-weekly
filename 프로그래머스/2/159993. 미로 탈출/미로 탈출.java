import java.util.*;

class Solution {
    
    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};
    
    int r, c;
    boolean[][] map;
        
    public int solution(String[] maps) {
        r = maps.length;
        c = maps[0].length();
        map = new boolean[r][c];
        
        int sy = 0, sx = 0;
        int ly = 0, lx = 0;
        int ey = 0, ex = 0;
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                char ch = maps[i].charAt(j);
                if (ch == 'O') {
                    map[i][j] = true;
                } else if (ch == 'S') {
                    map[i][j] = true;
                    sy = i;
                    sx = j;
                } else if (ch == 'E') {
                    map[i][j] = true;
                    ey = i;
                    ex = j;
                } else if (ch == 'L') {
                    map[i][j] = true;
                    ly = i;
                    lx = j;
                }
            }
        }
        
        int sl = bfs(sy, sx, ly, lx);
        int le = bfs(ly, lx, ey, ex);

        return (sl == -1 || le == -1) ? -1 : sl + le;
    }
    
    public int bfs(int sy, int sx, int ey, int ex) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sy, sx, 0});
        
        boolean[][] visited = new boolean[r][c];
        visited[sy][sx] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], dist = cur[2];
            
            if (y == ey && x == ex) {
                return dist;
            }

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];
                
                if (ny < 0 || ny >= r || nx < 0 || nx >= c || !map[ny][nx] || visited[ny][nx]) continue;
                
                visited[ny][nx] = true;
                q.offer(new int[] {ny, nx, dist + 1});
            }
        }
        
        return -1;
    }
    
}