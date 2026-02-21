import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    static int N;
    static int[][] map;
    static boolean[][] visited;

    static void label(int y, int x, int num) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;
        map[y][x] = num;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];

            for (int d = 0; d < 4; d++) {
                int ny = cy + DY[d];
                int nx = cx + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (visited[ny][nx] || map[ny][nx] == 0) continue;

                visited[ny][nx] = true;
                map[ny][nx] = num;
                q.offer(new int[]{ny, nx});
            }
        }
    }

    static int bfs(int num) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == num) {
                    q.offer(new int[]{i, j, 0});
                    v[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int dist = cur[2];

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (v[ny][nx]) continue;

                if (map[ny][nx] > 0 && map[ny][nx] != num) {
                    return dist;
                }

                if (map[ny][nx] == 0) {
                    v[ny][nx] = true;
                    q.offer(new int[]{ny, nx, dist + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    label(i, j, num++);
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < num; i++) {
            ans = Math.min(ans, bfs(i));
        }

        System.out.println(ans);
    }
}