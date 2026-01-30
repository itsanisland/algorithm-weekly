import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    static int N, M;
    static int[][] board;

    static void rotate(int x, int d, int k) {
        for (int i = 0; i < k; i++) {
            if (d == 0) {
                int tmp = board[x][M];
                System.arraycopy(board[x], 1, board[x], 2, M - 1);
                board[x][1] = tmp;
            } else {
                int tmp = board[x][1];
                System.arraycopy(board[x], 2, board[x], 1, M - 1);
                board[x][M] = tmp;
            }
        }
    }

    static void check() {
        int sum = 0, cnt = 0;
        boolean ck = false;

        int[][] tmp = new int[N + 1][M + 1];
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= M; x++) {
                tmp[y][x] = board[y][x];
            }
        }
        
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= M; x++) {
                int cur = board[y][x];
                
                if (cur == 0) continue;

                sum += cur;
                cnt++;
                
                for (int d = 0; d < 4; d++) {
                    int ny = y + DY[d];
                    int nx = x + DX[d];

                    if (ny < 1 || ny > N) continue;

                    if (nx < 1) nx = M;
                    if (nx > M) nx = 1;

                    if (cur == board[ny][nx]) {
                        tmp[y][x] = 0;
                        tmp[ny][nx] = 0;
                        ck = true;
                    }
                }
            }
        }

        if (ck) {
            board = tmp;
        } else {
            float avg = (float) sum / cnt;
            for (int y = 1; y <= N; y++) {
                for (int x = 1; x <= M; x++) {
                    if (board[y][x] == 0) continue;
                    if (board[y][x] > avg) board[y][x]--;
                    else if (board[y][x] < avg) board[y][x]++;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            for (int j = 1; j <= N; j++) {
                if (j % x == 0) rotate(j, d, k);
            }

            check();
        }

        int ans = 0;
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= M; x++) {
                ans += board[y][x];
            }
        }
        
        System.out.println(ans);
    }
}