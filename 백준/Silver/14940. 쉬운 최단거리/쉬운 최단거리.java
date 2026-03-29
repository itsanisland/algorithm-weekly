import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    static int N, M;
    static int[][] board, result;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int sy = 0, sx = 0;
        board = new int[N][M];
        result = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    sy = i;
                    sx = j;
                }
            }
        }

        bfs(sy, sx);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print((board[i][j] == 1 && result[i][j] == 0 ? -1 : result[i][j]) + " ");
            }
            System.out.println();
        }
    }

    static void bfs(int sy, int sx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sy, sx, 0});

        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], dist = cur[2];
            result[y][x] = dist;

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];
                
                if (ny < 0 || ny == N || nx < 0 || nx == M || board[ny][nx] == 0 || visited[ny][nx]) continue;

                q.offer(new int[]{ny, nx, dist + 1});
                visited[ny][nx] = true;
            }
        }
    }
}