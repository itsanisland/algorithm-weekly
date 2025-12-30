import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { -1, 1, 0, 0 };
    public final static int[] DX = { 0, 0, -1, 1 };

    public static int n, k;
    public static int[][] map;
    public static boolean[][] visited;

    public static int bfs(int y, int x, int c, boolean r) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { y, x });

        boolean[][] visitedCnt = new boolean[n + 2][12];
        visitedCnt[y][x] = true;

        if (r) {
            map[y][x] = 0;
            visited[y][x] = true;
        }

        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + DY[i];
                int nx = cx + DX[i];

                if (map[ny][nx] == c && !visitedCnt[ny][nx]) {
                    visitedCnt[ny][nx] = true;
                    if (r) {
                        map[ny][nx] = 0;
                        visited[ny][nx] = true;
                    }
                    q.offer(new int[] { ny, nx });
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static boolean check() {
        visited = new boolean[n + 2][12];
        boolean ret = false;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < 11; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    int cnt = bfs(i, j, map[i][j], false);
                    if (cnt >= k) {
                        bfs(i, j, map[i][j], true);
                        ret = true;
                    }
                }
            }
        }
        return ret;
    }

    public static void fall() {
        for (int i = n; i > 0; i--) {
            for (int j = 1; j < 12; j++) {
                if (map[i][j] == 0) {
                    int p = i;
                    while (p > 0 && map[p][j] == 0) p--;
                    map[i][j] = map[p][j];
                    map[p][j] = 0;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        map = new int[n + 2][12];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < 10; j++) {
                map[i + 1][j + 1] = s.charAt(j) - '0';
            }
        }

        while (check()) {
            fall();
        }
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < 11; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}