import java.util.*;
import java.io.*;

class Main {

    static class Guest {
        int sy, sx, dy, dx;
        Guest(int sy, int sx, int dy, int dx) {
            this.sy = sy; this.sx = sx;
            this.dy = dy; this.dx = dx;
        }
    }

    // 위, 왼쪽, 오른쪽, 아래 (행/열 우선 조건 보장)
    static final int[] DY = {-1, 0, 0, 1};
    static final int[] DX = {0, -1, 1, 0};

    static int N, M, F;
    static int[][] map;
    static Guest[] guests;
    static int[][] guestIdx; // 손님 있으면 index, 없으면 -1

    // 택시 위치에서 가장 먼저 만나는 손님 찾기
    static int findGuest(int ty, int tx) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
    
        q.offer(new int[]{ty, tx});
        visited[ty][tx] = true;
    
        int dist = 0;
    
        while (!q.isEmpty()) {
            int size = q.size();
            int selIdx = -1;
            int minY = Integer.MAX_VALUE;
            int minX = Integer.MAX_VALUE;
    
            // 같은 거리 레벨만 처리
            for (int s = 0; s < size; s++) {
                int[] cur = q.poll();
                int y = cur[0], x = cur[1];
    
                if (guestIdx[y][x] != -1) {
                    if (y < minY || (y == minY && x < minX)) {
                        minY = y;
                        minX = x;
                        selIdx = guestIdx[y][x];
                    }
                }
    
                for (int d = 0; d < 4; d++) {
                    int ny = y + DY[d];
                    int nx = x + DX[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                    if (map[ny][nx] == 1 || visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }
    
            // 이 거리에서 손님을 찾았으면 종료
            if (selIdx != -1) {
                if (F < dist) return -1;
                F -= dist;
                return selIdx;
            }
    
            dist++;
        }
    
        return -1;
    }

    // 출발 → 도착 BFS
    static int move(int sy, int sx, int dy, int dx) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new int[]{sy, sx, 0});
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], dist = cur[2];

            if (y == dy && x == dx) return dist;

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                if (map[ny][nx] == 1 || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx, dist + 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        guestIdx = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(guestIdx[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int ty = Integer.parseInt(st.nextToken()) - 1;
        int tx = Integer.parseInt(st.nextToken()) - 1;

        guests = new Guest[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int dy = Integer.parseInt(st.nextToken()) - 1;
            int dx = Integer.parseInt(st.nextToken()) - 1;
            guests[i] = new Guest(sy, sx, dy, dx);
            guestIdx[sy][sx] = i;
        }

        for (int i = 0; i < M; i++) {
            int idx = findGuest(ty, tx);
            if (idx == -1) {
                System.out.println(-1);
                return;
            }

            Guest g = guests[idx];
            guestIdx[g.sy][g.sx] = -1;

            int d = move(g.sy, g.sx, g.dy, g.dx);
            if (d == -1 || F < d) {
                System.out.println(-1);
                return;
            }

            F += d;
            ty = g.dy;
            tx = g.dx;
        }

        System.out.println(F);
    }
}