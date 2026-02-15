import java.io.*;
import java.util.*;

public class Main {

    static int R, C, K;
    static int[][] board;
    static boolean[][][] wall; // [R][C][5]
    static List<int[]> heaters = new ArrayList<>();
    static List<int[]> targets = new ArrayList<>();

    // 1: 오른쪽, 2: 왼쪽, 3: 위, 4: 아래
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        wall = new boolean[R][C][5];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (v >= 1 && v <= 4) heaters.add(new int[]{i, j, v});
                if (v == 5) targets.add(new int[]{i, j});
            }
        }

        int W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) { // 위쪽 벽
                wall[x][y][3] = true;
                wall[x - 1][y][4] = true;
            } else { // 오른쪽 벽
                wall[x][y][1] = true;
                wall[x][y + 1][2] = true;
            }
        }

        int chocolate = 0;

        while (chocolate <= 100) {
            // 1. 온풍기 작동
            for (int[] h : heaters) {
                operate(h[0], h[1], h[2]);
            }

            // 2. 온도 조절
            adjustTemp();

            // 3. 가장자리 감소
            dropTemp();

            chocolate++;

            // 4. 조사 칸 확인
            if (check()) break;
        }

        System.out.println(chocolate);
    }

    static void operate(int x, int y, int dir) {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new ArrayDeque<>();
    
        int sx = x + dx[dir];
        int sy = y + dy[dir];
    
        // 시작점 범위 체크
        if (sx < 0 || sx >= R || sy < 0 || sy >= C) return;
    
        board[sx][sy] += 5;
        visited[sx][sy] = true;
        q.offer(new int[]{sx, sy, 5});
    
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int temp = cur[2];
    
            if (temp == 1) continue;
    
            for (int i = -1; i <= 1; i++) {
    
                int tx = cx;
                int ty = cy;

                // 방향별 좌표 이동 계산
                if (dir == 1) { // 오른쪽
                    if (i == -1) { // 위대각
                        // 위쪽 벽 체크
                        if (wall[cx][cy][3]) continue;
                        tx--;
                    }
                    if (i == 1) { // 아래대각
                        if (wall[cx][cy][4]) continue;
                        tx++;
                    }
    
                    // 범위 체크
                    if (tx < 0 || tx >= R) continue;
    
                    // 오른쪽 벽 체크
                    if (wall[tx][ty][1]) continue;
    
                    ty++;
    
                } else if (dir == 2) { // 왼쪽
                    if (i == -1) {
                        if (wall[cx][cy][3]) continue;
                        tx--;
                    }
                    if (i == 1) {
                        if (wall[cx][cy][4]) continue;
                        tx++;
                    }
    
                    if (tx < 0 || tx >= R) continue;
    
                    if (wall[tx][ty][2]) continue;
    
                    ty--;
    
                } else if (dir == 3) { // 위
                    if (i == -1) {
                        if (wall[cx][cy][2]) continue;
                        ty--;
                    }
                    if (i == 1) {
                        if (wall[cx][cy][1]) continue;
                        ty++;
                    }
    
                    if (ty < 0 || ty >= C) continue;
    
                    if (wall[tx][ty][3]) continue;
    
                    tx--;
    
                } else if (dir == 4) { // 아래
                    if (i == -1) {
                        if (wall[cx][cy][2]) continue;
                        ty--;
                    }
                    if (i == 1) {
                        if (wall[cx][cy][1]) continue;
                        ty++;
                    }
    
                    if (ty < 0 || ty >= C) continue;
    
                    if (wall[tx][ty][4]) continue;
    
                    tx++;
                }
                
                if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;
                if (visited[tx][ty]) continue;
    
                visited[tx][ty] = true;
                board[tx][ty] += temp - 1;
                q.offer(new int[]{tx, ty, temp - 1});
            }
        }
    }

    static void adjustTemp() {
        int[][] diff = new int[R][C];

        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {

                // 오른쪽
                if (y + 1 < C && !wall[x][y][1]) {
                    int d = (board[x][y] - board[x][y + 1]) / 4;
                    if (d > 0) {
                        diff[x][y] -= d;
                        diff[x][y + 1] += d;
                    } else {
                        diff[x][y] += -d;
                        diff[x][y + 1] -= -d;
                    }
                }

                // 아래
                if (x + 1 < R && !wall[x][y][4]) {
                    int d = (board[x][y] - board[x + 1][y]) / 4;
                    if (d > 0) {
                        diff[x][y] -= d;
                        diff[x + 1][y] += d;
                    } else {
                        diff[x][y] += -d;
                        diff[x + 1][y] -= -d;
                    }
                }
            }
        }

        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                board[i][j] += diff[i][j];
    }

    static void dropTemp() {
        for (int y = 0; y < C; y++) {
            if (board[0][y] > 0) board[0][y]--;
            if (board[R - 1][y] > 0) board[R - 1][y]--;
        }

        for (int x = 1; x < R - 1; x++) {
            if (board[x][0] > 0) board[x][0]--;
            if (board[x][C - 1] > 0) board[x][C - 1]--;
        }
    }

    static boolean check() {
        for (int[] t : targets) {
            if (board[t[0]][t[1]] < K) return false;
        }
        return true;
    }
}