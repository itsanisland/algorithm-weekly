import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {0, 1, 0, -1};
    static final int[] DX = {1, 0, -1, 0};

    static int N, M, ans;
    static int[][] map;
    static int[] dice = {0, 1, 2, 3, 4, 5, 6};

    static void roll(int d) {
        int tmp;
        switch (d) {
            case 0: // 동
                tmp = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = tmp;
                break;
            case 1: // 남
                tmp = dice[6];
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = tmp;
                break;
            case 2: // 서
                tmp = dice[6];
                dice[6] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = tmp;
                break;
            case 3: // 북
                tmp = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = tmp;
                break;
        }
    }

    static int bfs(int y, int x, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {y, x});

        boolean[][] visited = new boolean[N][M];
        visited[y][x] = true;

        int cnt = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            y = cur[0];
            x = cur[1];
            cnt++;
            
            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];
    
                if (ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] != b || visited[ny][nx]) continue;
    
                visited[ny][nx] = true;
                q.offer(new int[] {ny, nx});
            }
        }

        return cnt;
    }
    
    static int[] play(int[] yxd) {
        int y = yxd[0], x = yxd[1], d = yxd[2];
        
        int ny = y + DY[d];
        int nx = x + DX[d];

        if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
            d = (d + 2) % 4;
            ny = y + DY[d];
            nx = x + DX[d];
        }

        roll(d);

        int A = dice[6];
        int B = map[ny][nx];
        int C = bfs(ny, nx, B);

        ans += B * C;

        if (A > B) d = (d + 1) % 4;
        else if (A < B) d = (d + 3) % 4;

        return new int[] {ny, nx, d};
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[] yxd = {0, 0, 0};
        
        while (K-- > 0) {
            yxd = play(yxd);
        }
        
        System.out.println(ans);
    }
}