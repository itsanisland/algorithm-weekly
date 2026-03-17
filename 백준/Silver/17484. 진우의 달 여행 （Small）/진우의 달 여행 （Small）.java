import java.util.*;
import java.io.*;

class Main {

    static final int[] DX = {-1, 0, 1};

    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] space;
    
    static void dfs(int y, int x, int dir, int depth, int sum) {
        if (depth == N - 1) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int d = 0; d < 3; d++) {
            int ny = y + 1;
            int nx = x + DX[d];
            if (nx < 0 || nx == M || d == dir) continue;
            dfs(ny, nx, d, depth + 1, sum + space[ny][nx]);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            dfs(0, i, -1, 0, space[0][i]);
        }
        
        System.out.println(ans);
    }
}