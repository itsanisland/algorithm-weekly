import java.io.*;
import java.util.*;

public class Main {

    static class Fish {
        int y, x, d;
        Fish(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    // ↑ ← ↖ ↓ ↘ → ↗ ← (문제 방향 그대로)
    static final int[] dy = {-1,-1,0,1,1,1,0,-1};
    static final int[] dx = {0,-1,-1,-1,0,1,1,1};

    static int answer = 0;

    static void dfs(int[][] board, Fish[] fishes,
                    int sy, int sx, int sd, int sum) {

        answer = Math.max(answer, sum);

        /* 1) 물고기 이동 */
        for (int i = 1; i <= 16; i++) {
            Fish f = fishes[i];
            if (f == null) continue;

            for (int r = 0; r < 8; r++) {
                int nd = (f.d + r) % 8;
                int ny = f.y + dy[nd];
                int nx = f.x + dx[nd];

                if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue;
                if (ny == sy && nx == sx) continue;

                int target = board[ny][nx];

                // swap
                board[f.y][f.x] = target;
                board[ny][nx] = i;

                if (target != 0) {
                    fishes[target].y = f.y;
                    fishes[target].x = f.x;
                }

                f.y = ny;
                f.x = nx;
                f.d = nd;
                break;
            }
        }

        /* 2) 상어 이동 */
        for (int step = 1; step <= 3; step++) {
            int ny = sy + dy[sd] * step;
            int nx = sx + dx[sd] * step;

            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) break;
            if (board[ny][nx] == 0) continue;

            // 깊은 복사
            int[][] nextBoard = new int[4][4];
            for (int i = 0; i < 4; i++)
                nextBoard[i] = board[i].clone();

            Fish[] nextFish = new Fish[17];
            for (int i = 1; i <= 16; i++) {
                if (fishes[i] != null) {
                    Fish f = fishes[i];
                    nextFish[i] = new Fish(f.y, f.x, f.d);
                }
            }

            int eaten = nextBoard[ny][nx];
            nextBoard[sy][sx] = 0;
            nextBoard[ny][nx] = -1;
            nextFish[eaten] = null;

            dfs(nextBoard, nextFish, ny, nx,
                fishes[eaten].d, sum + eaten);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[4][4];
        Fish[] fishes = new Fish[17];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                board[i][j] = num;
                fishes[num] = new Fish(i, j, dir);
            }
        }

        int first = board[0][0];
        int sd = fishes[first].d;
        board[0][0] = -1;
        fishes[first] = null;

        dfs(board, fishes, 0, 0, sd, first);

        System.out.println(answer);
    }
}