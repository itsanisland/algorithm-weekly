import java.util.*;

class Solution {
    static final int SIZE = 102;  // 좌표 최대 50 → 2배 + 여유
    int[] DY = {1, -1, 0, 0};
    int[] DX = {0, 0, -1, 1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] board = new int[SIZE][SIZE];
        
        // 모든 사각형 내부를 1로 채우기
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            for (int i = y1; i <= y2; i++)
                for (int j = x1; j <= x2; j++)
                    board[i][j] = 1;
        }
        
        // 내부(둘러싸인 칸) 지우기 → 테두리만 남기기
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            for (int i = y1 + 1; i < y2; i++)
                for (int j = x1 + 1; j < x2; j++)
                    board[i][j] = 0;
        }
        
        // BFS
        int answer = bfs(board, characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
        return answer;
    }
    
    private int bfs(int[][] board, int characterX, int characterY, int itemX, int itemY) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {characterY, characterX, 0});
        
        boolean[][] visited = new boolean[SIZE][SIZE];
        visited[characterY][characterX] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], d = cur[2];
            
            if (y == itemY && x == itemX) {
                return d;
            }
            
            for (int i = 0; i < 4; i++) {
                int ny = y + DY[i];
                int nx = x + DX[i];
                
                if (!visited[ny][nx] && board[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    q.offer(new int[] {ny, nx, d + 1});
                }
            }
        }
        
        return -1;
    }
}