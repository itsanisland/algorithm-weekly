import java.util.*;

class Solution {
    static final int[] DY = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static final int[] DX = {0, 0, -1, 1};

    static class State {
        int y, x, dir, time;
        State(int y, int x, int dir, int time) {
            this.y = y;
            this.x = x;
            this.dir = dir; // 0 = 가로, 1 = 세로
            this.time = time;
        }
    }

    public int solution(int[][] board) {
        int N = board.length;
        // ----- 1️⃣ 패딩 보드 생성 -----
        int[][] A = new int[N + 2][N + 2];
        for (int i = 0; i < N + 2; i++) Arrays.fill(A[i], 1); // 가장자리는 벽(1)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i + 1][j + 1] = board[i][j]; // 실제 보드는 1~N 영역에 위치

        boolean[][][] vis = new boolean[N + 2][N + 2][2];
        Queue<State> q = new ArrayDeque<>();

        // ----- 2️⃣ 시작 상태: (1,1) 가로 -----
        q.offer(new State(1, 1, 0, 0));
        vis[1][1][0] = true;

        // ----- 3️⃣ BFS -----
        while (!q.isEmpty()) {
            State cur = q.poll();
            int y = cur.y, x = cur.x, d = cur.dir, t = cur.time;

            // ----- 4️⃣ 도착 체크 -----
            if (d == 0 && y == N && x + 1 == N) return t;   // 가로 (오른쪽 칸이 도착점)
            if (d == 1 && y + 1 == N && x == N) return t;   // 세로 (아래쪽 칸이 도착점)

            // ----- 5️⃣ 평행이동 -----
            for (int i = 0; i < 4; i++) {
                int ny = y + DY[i], nx = x + DX[i];
                if (canMove(A, ny, nx, d) && !vis[ny][nx][d]) {
                    vis[ny][nx][d] = true;
                    q.offer(new State(ny, nx, d, t + 1));
                }
            }

            // ----- 6️⃣ 회전 -----
            if (d == 0) rotateHorizontal(A, vis, q, y, x, t);
            else rotateVertical(A, vis, q, y, x, t);
        }

        return -1; // 도달 불가 (문제 조건상 나오지 않음)
    }

    // 두 칸이 모두 빈칸인지 확인
    private boolean canMove(int[][] A, int y, int x, int dir) {
        return dir == 0
                ? (A[y][x] == 0 && A[y][x + 1] == 0)   // 가로: (y,x),(y,x+1)
                : (A[y][x] == 0 && A[y + 1][x] == 0);  // 세로: (y,x),(y+1,x)
    }

    // 가로 → 세로 회전
    private void rotateHorizontal(int[][] A, boolean[][][] vis, Queue<State> q, int y, int x, int t) {
        // 위로 회전
        if (A[y - 1][x] == 0 && A[y - 1][x + 1] == 0) {
            if (!vis[y - 1][x][1]) { vis[y - 1][x][1] = true; q.offer(new State(y - 1, x, 1, t + 1)); }
            if (!vis[y - 1][x + 1][1]) { vis[y - 1][x + 1][1] = true; q.offer(new State(y - 1, x + 1, 1, t + 1)); }
        }
        // 아래로 회전
        if (A[y + 1][x] == 0 && A[y + 1][x + 1] == 0) {
            if (!vis[y][x][1]) { vis[y][x][1] = true; q.offer(new State(y, x, 1, t + 1)); }
            if (!vis[y][x + 1][1]) { vis[y][x + 1][1] = true; q.offer(new State(y, x + 1, 1, t + 1)); }
        }
    }

    // 세로 → 가로 회전
    private void rotateVertical(int[][] A, boolean[][][] vis, Queue<State> q, int y, int x, int t) {
        // 왼쪽으로 회전
        if (A[y][x - 1] == 0 && A[y + 1][x - 1] == 0) {
            if (!vis[y][x - 1][0]) { vis[y][x - 1][0] = true; q.offer(new State(y, x - 1, 0, t + 1)); }
            if (!vis[y + 1][x - 1][0]) { vis[y + 1][x - 1][0] = true; q.offer(new State(y + 1, x - 1, 0, t + 1)); }
        }
        // 오른쪽으로 회전
        if (A[y][x + 1] == 0 && A[y + 1][x + 1] == 0) {
            if (!vis[y][x][0]) { vis[y][x][0] = true; q.offer(new State(y, x, 0, t + 1)); }
            if (!vis[y + 1][x][0]) { vis[y + 1][x][0] = true; q.offer(new State(y + 1, x, 0, t + 1)); }
        }
    }
}