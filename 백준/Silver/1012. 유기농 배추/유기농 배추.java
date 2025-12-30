import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { -1, 1, 0, 0 };
    public final static int[] DX = { 0, 0, -1, 1 };

    public static int n, m;
    public static boolean[][] map;
    public static boolean[][] visited;

    public static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    public static void bfs(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { y, x });
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + DY[i];
                int nx = cx + DX[i];

                if (isValid(ny, nx) && map[ny][nx] && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new int[] { ny, nx });
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            map = new boolean[n][m];
            visited = new boolean[n][m];
    
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = true;
            }

            int ans = 0;
            
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (map[y][x] && !visited[y][x]) {
                        ans++;
                        bfs(y, x);
                    }
                }
            }
    
            System.out.println(ans);
        }
    }
}