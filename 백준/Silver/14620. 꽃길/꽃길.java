import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { -1, 1, 0, 0 };
    public final static int[] DX = { 0, 0, -1, 1 };

    public static int n, min = 3_000;
    public static int[][] map;
    public static boolean[][] visited;

    public static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    public static int flower(int p) {
        // 1차원 -> 2차원
        int y = p / n;
        int x = p % n;

        visited[y][x] = true;

        int sum = map[y][x];
        for (int i = 0; i < 4; i++) {
            int ny = y + DY[i];
            int nx = x + DX[i];

            if (isValid(ny, nx)) {
                if (visited[ny][nx]) {
                    return 3_000;
                }
                sum += map[ny][nx];
                visited[ny][nx] = true;
            } else {
                return 3_000;
            }
        }

        return sum;
    }

    public static void solve(int i, int j, int k) {
        visited = new boolean[n][n];
        
        int sum = 0;
        sum += flower(i);
        sum += flower(j);
        sum += flower(k);

        min = Math.min(min, sum);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 위치 3군데 뽑기
        // 2차원 데이터를 하나의 값으로 표현
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                for (int k = 0; k < n * n; k++) {
                    solve(i, j, k);
                }
            }
        }

        System.out.println(min);
    }
}