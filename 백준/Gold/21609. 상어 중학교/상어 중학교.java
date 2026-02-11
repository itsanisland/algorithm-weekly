import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    static int N, M;
    static int[][] board;
    static int[] largest;

    static void find(int sy, int sx, boolean[][] globalVisited) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        List<int[]> rainbow = new ArrayList<>();

        int color = board[sy][sx];

        q.offer(new int[]{sy, sx});
        visited[sy][sx] = true;
        globalVisited[sy][sx] = true;

        int cnt = 1;
        int rainbowCnt = 0;

        int by = sy, bx = sx; // 기준 블록

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (visited[ny][nx]) continue;
                if (board[ny][nx] == -1 || board[ny][nx] == -2) continue;
                if (board[ny][nx] != 0 && board[ny][nx] != color) continue;

                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx});
                cnt++;

                if (board[ny][nx] == 0) {
                    rainbow.add(new int[]{ny, nx});
                    rainbowCnt++;
                } else {
                    globalVisited[ny][nx] = true;

                    // 기준 블록은 (행 작은 것, 열 작은 것)
                    if (board[ny][nx] > 0) {
                        if (ny < by || (ny == by && nx < bx)) {
                            by = ny;
                            bx = nx;
                        }
                    }
                }
            }
        }

        // 무지개 블록은 globalVisited에 표시하지 않음
        // (재사용 가능해야 함)

        if (cnt < 2) return;

        // 우선순위 비교
        if (largest[2] < cnt ||
           (largest[2] == cnt &&
            (rainbowCnt > largest[4] ||
             (rainbowCnt == largest[4] &&
              (by > largest[0] ||
               (by == largest[0] && bx > largest[1])))))) {
            largest[0] = by;
            largest[1] = bx;
            largest[2] = cnt;
            largest[3] = color;
            largest[4] = rainbowCnt;
        }
    }

    static void remove(int sy, int sx, int color) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new int[]{sy, sx});
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            board[y][x] = -2;

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (visited[ny][nx]) continue;
                if (board[ny][nx] == -1 || board[ny][nx] == -2) continue;
                if (board[ny][nx] != 0 && board[ny][nx] != color) continue;

                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx});
            }
        }
    }

    static void press() {
        for (int x = 0; x < N; x++) {
            int bottom = N - 1;
            for (int y = N - 1; y >= 0; y--) {
                if (board[y][x] == -1) {
                    bottom = y - 1;
                } else if (board[y][x] >= 0) {
                    int temp = board[y][x];
                    board[y][x] = -2;
                    board[bottom][x] = temp;
                    bottom--;
                }
            }
        }
    }

    static void rotate() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[N - j - 1][i] = board[i][j]; // 반시계 90도
            }
        }
        board = tmp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;

        while (true) {
            largest = new int[]{-1, -1, 0, 0, 0}; 
            boolean[][] globalVisited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] > 0 && !globalVisited[i][j]) {
                        find(i, j, globalVisited);
                    }
                }
            }

            if (largest[2] < 2) break;

            score += largest[2] * largest[2];

            remove(largest[0], largest[1], largest[3]);

            press();
            rotate();
            press();
        }

        System.out.println(score);
    }
}