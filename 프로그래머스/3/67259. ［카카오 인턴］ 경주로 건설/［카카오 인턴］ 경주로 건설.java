import java.util.*;

class Solution {
    
    int INF = Integer.MAX_VALUE;
    int[] DY = { -1, 1, 0, 0 };
    int[] DX = { 0, 0, -1, 1 };
    
    int N, M;
    int[][] B;
    int[][][] minCost;
    
    public int solution(int[][] board) {
        N = board.length;
        M = board[0].length;
        
        B = board;
        
        minCost = new int[N][M][4];
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) Arrays.fill(minCost[i][j], INF);
        
        dijkstra();
        
        return Arrays.stream(minCost[N - 1][M - 1]).min().getAsInt();
    }
    
    void dijkstra() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        
        for (int i = 0; i < 4; i++) {
            pq.add(new Pos(0, 0, i, 0));
            minCost[0][0][i] = 0;
        }
        
        while (!pq.isEmpty()) {
            Pos cur = pq.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + DY[i], nx = cur.x + DX[i];
                
                if (!isValid(ny, nx) || B[ny][nx] == 1) continue;
                
                int cost = cur.cost + ((cur.cost == 0 || cur.dir == i) ? 100 : 600);
                
                if (cost < minCost[ny][nx][i]) {
                    minCost[ny][nx][i] = cost;
                    pq.offer(new Pos(ny, nx, i, cost));   
                }
            }
        }
    }
    
    boolean isValid(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
    
    class Pos implements Comparable<Pos> {
        int y, x, dir, cost;
        
        Pos(int y, int x, int dir, int cost) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Pos o) {
            return Integer.compare(cost, o.cost);
        }
    }
}