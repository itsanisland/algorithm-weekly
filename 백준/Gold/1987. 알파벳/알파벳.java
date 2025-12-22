import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { -1, 1, 0, 0 };
    public final static int[] DX = { 0, 0, -1, 1 };

    public static int R, C, ans;
    public static char[][] map;
    public static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        ans = 0;

        map = new char[R][C];
        visited = new boolean[26];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        
        System.out.println(ans);
    }

    public static void dfs(int y, int x, int cnt) {
        ans = Math.max(ans, cnt);

        for (int i = 0; i < 4; i++) {
            int ny = y + DY[i];
            int nx = x + DX[i];

            if (isValid(ny, nx) && !visited[map[ny][nx] - 'A']) {
                visited[map[ny][nx] - 'A'] = true;
                dfs(ny, nx, cnt + 1);
                visited[map[ny][nx] - 'A'] = false;
            }            
        }
    }

    public static boolean isValid(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
}