import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = {-1, 1, 0, 0};
    public final static int[] DX = {0, 0, -1, 1};

    public final static int[][] T = {
        {0, 0}, {0, 1}, {0, 2}, {1, 1},
        {0, 0}, {1, 0}, {2, 0}, {1, 1},
        {0, 0}, {0, 1}, {0, 2}, {1, -1},
        {0, 0}, {0, 1}, {0, 2}, {1, 1}
    };

    public static int N, M;
    public static int[][] map;
    public static boolean[][] visited;
    public static int max;

    public static void dfs(int y, int x, int cnt, int sum) {
        if (cnt == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + DY[i];
            int nx = x + DX[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            if (visited[ny][nx]) continue;

            visited[ny][nx] = true;
            dfs(ny, nx, cnt + 1, sum + map[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    static void checkT(int y, int x) {
        // 중심 + 상/하/좌/우 중 3개
        int center = map[y][x];

        for (int skip = 0; skip < 4; skip++) {
            int sum = center;
            boolean valid = true;

            for (int dir = 0; dir < 4; dir++) {
                if (dir == skip) continue;

                int ny = y + DY[dir];
                int nx = x + DX[dir];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    valid = false;
                    break;
                }

                sum += map[ny][nx];
            }

            if (valid) {
                max = Math.max(max, sum);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // DFS (ㅡ, ㅁ, L, S 모양)
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;

                // T 모양 체크
                checkT(i, j);
            }
        }        
        
        System.out.println(max);
    }
}