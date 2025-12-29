import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { 0, -1, 1, 0, 0 };
    public final static int[] DX = { 0, 0, 0, -1, 1 };

    public static int n, min = 3_000;
    public static int[][] map;
    public static boolean[][] visited;

    public static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    public static int flower(int y, int x, boolean v) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            int ny = y + DY[i];
            int nx = x + DX[i];

            sum += map[ny][nx];
            visited[ny][nx] = v;
        }
        return sum;
    }

    public static boolean isVisited(int y, int x) {
        for (int i = 0; i < 5; i++) {
            int ny = y + DY[i];
            int nx = x + DX[i];

            if (visited[ny][nx]) {
                return true;
            }
        }
        return false;
    }

    public static void dfs(int cnt, int sum) {
        if (cnt == 3) {
            min = Math.min(min, sum);
            return;
        }
        
        for (int y = 1; y < n - 1; y++) {
            for (int x = 1; x < n - 1; x++) {
                if (!isVisited(y, x)) {
                    dfs(cnt + 1, sum + flower(y, x, true));
                    flower(y, x, false);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(min);
    }
}