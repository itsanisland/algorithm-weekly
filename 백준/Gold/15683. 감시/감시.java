import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<CCTV> cctvs = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    // 상, 우, 하, 좌
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    // CCTV 타입별 방향 조합
    static int[][][] dirs = {
        {},
        {{0}, {1}, {2}, {3}},                 // 1번
        {{0, 2}, {1, 3}},                     // 2번
        {{0, 1}, {1, 2}, {2, 3}, {3, 0}},     // 3번
        {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번
        {{0, 1, 2, 3}}                        // 5번
    };

    static class CCTV {
        int type, y, x;
        CCTV(int type, int y, int x) {
            this.type = type;
            this.y = y;
            this.x = x;
        }
    }

    static void dfs(int idx, int[][] board) {
        if (idx == cctvs.size()) {
            min = Math.min(min, countBlind(board));
            return;
        }

        CCTV c = cctvs.get(idx);

        for (int[] dirSet : dirs[c.type]) {
            int[][] copied = copy(board);

            for (int d : dirSet) {
                int ny = c.y;
                int nx = c.x;

                while (true) {
                    ny += dy[d];
                    nx += dx[d];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) break;
                    if (copied[ny][nx] == 6) break;

                    if (copied[ny][nx] == 0) {
                        copied[ny][nx] = -1;
                    }
                }
            }
            dfs(idx + 1, copied);
        }
    }

    static int countBlind(int[][] board) {
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (board[i][j] == 0) cnt++;
        return cnt;
    }

    static int[][] copy(int[][] src) {
        int[][] dst = new int[N][M];
        for (int i = 0; i < N; i++)
            dst[i] = src[i].clone();
        return dst;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new CCTV(map[i][j], i, j));
                }
            }
        }

        dfs(0, map);
        System.out.println(min);
    }
}