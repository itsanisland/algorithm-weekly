import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { -1, 1, 0, 0 };
    public final static int[] DX = { 0, 0, -1, 1 };

    public static int N, M;
    public static char[][] B;

    public static int bfs(int ry, int rx, int by, int bx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { ry, rx, by, bx, 0 });
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[ry][rx][by][bx] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cry = cur[0], crx = cur[1];
            int cby = cur[2], cbx = cur[3];
            int cnt = cur[4];

            if (cnt + 1 > 10) continue; // 해당 방향 무효
            
            boolean redFirst = false;
            for (int dir = 0; dir < 4; dir++) {
                switch (dir) {
                    case 0: // 상
                        redFirst = cry < cby;
                        break;
                    case 1: // 하
                        redFirst = cry > cby;
                        break;
                    case 2: // 좌
                        redFirst = crx < cbx;
                        break;
                    case 3: // 우
                        redFirst = crx > cbx;
                        break;
                }

                int nry = cry, nrx = crx;
                int nby = cby, nbx = cbx;

                if (redFirst) {
                    // moveRed();
                    while (B[nry + DY[dir]][nrx + DX[dir]] != '#') {
                        nry += DY[dir];
                        nrx += DX[dir];
                        if (B[nry][nrx] == 'O') break;
                    }
                    // moveBlue();
                    while (B[nby + DY[dir]][nbx + DX[dir]] != '#') {
                        nby += DY[dir];
                        nbx += DX[dir];
                        if (B[nby][nbx] == 'O') break;
                    }
                } else {
                    // moveBlue();
                    while (B[nby + DY[dir]][nbx + DX[dir]] != '#') {
                        nby += DY[dir];
                        nbx += DX[dir];
                        if (B[nby][nbx] == 'O') break;
                    }
                    // moveRed();
                    while (B[nry + DY[dir]][nrx + DX[dir]] != '#') {
                        nry += DY[dir];
                        nrx += DX[dir];
                        if (B[nry][nrx] == 'O') break;
                    }
                }

                // 1. 파란 구슬이 빠졌다면 → 무조건 실패
                if (B[nby][nbx] == 'O') continue;
                
                // 2. 빨간 구슬만 빠졌다면 → 성공
                if (B[nry][nrx] == 'O') return cnt + 1;
                
                // 3. 둘 다 구멍이 아니라면 → 겹침 처리
                if (nry == nby && nrx == nbx) {
                    if (redFirst) {
                        nby -= DY[dir];
                        nbx -= DX[dir];
                    } else {
                        nry -= DY[dir];
                        nrx -= DX[dir];
                    }
                }

                if (cnt + 1 > 10) continue; // 해당 방향 무효

                if (!visited[nry][nrx][nby][nbx]) {
                    // R, B 겹침 처리
                    if (nry == nby && nrx == nbx) {
                        if (redFirst) {
                            nby -= DY[dir];
                            nbx -= DX[dir];
                        } else {
                            nry -= DY[dir];
                            nrx -= DX[dir];
                        }
                    }
                    
                    q.offer(new int[] { nry, nrx, nby, nbx, cnt + 1 });
                    visited[nry][nrx][nby][nbx] = true;
                }
            }
        }

        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = new char[N][M];

        int ry = 0, rx = 0, by = 0, bx = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                B[i][j] = s.charAt(j);
                if (B[i][j] == 'R') {
                    ry = i;
                    rx = j;
                    B[i][j] = '.';
                }
                if (B[i][j] == 'B') {
                    by = i;
                    bx = j;
                    B[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(ry, rx, by, bx));
    }
}