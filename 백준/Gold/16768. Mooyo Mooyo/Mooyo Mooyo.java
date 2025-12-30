import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { -1, 1, 0, 0 };
    public final static int[] DX = { 0, 0, -1, 1 };

    public static int n, k;
    public static int[][] map;
    public static boolean[][] visited, visitedCnt;

    public static int dfsCnt(int y, int x) {
        int ret = 1;
        visitedCnt[y][x] = true;
        
        for (int i = 0; i < 4; i++) {
            int ny = y + DY[i];
            int nx = x + DX[i];

            if (visitedCnt[ny][nx] || map[y][x] != map[ny][nx]) continue;
            ret += dfsCnt(ny, nx);
        }
        return ret;
    }

    public static void dfsRemove(int y, int x, int c) {
        visited[y][x] = true;
        map[y][x] = 0;
        
        for (int i = 0; i < 4; i++) {
            int ny = y + DY[i];
            int nx = x + DX[i];

            if (visited[ny][nx] || map[ny][nx] != c) continue;
            dfsRemove(ny, nx, c);
        }
    }

    public static boolean check() {
        visited = new boolean[n + 2][12];
        boolean ret = false;
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < 11; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    visitedCnt = new boolean[n + 2][12];
                    int cnt = dfsCnt(i, j);
                    
                    if (cnt >= k) {
                        dfsRemove(i, j, map[i][j]);
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