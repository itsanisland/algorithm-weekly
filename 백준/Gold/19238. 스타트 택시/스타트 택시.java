import java.util.*;
import java.io.*;

class Main {

    static class Guest implements Comparable<Guest> {
        int sy, sx, dy, dx, dist;

        Guest(int sy, int sx, int dy, int dx, int dist) {
            this.sy = sy; this.sx = sx;
            this.dy = dy; this.dx = dx;
            this.dist = dist;
        }

        public int compareTo(Guest o) {
            if (this.dist == o.dist) {
                if (this.sy == o.sy) return this.sx - o.sx;
                return this.sy - o.sy;
            }
            return this.dist - o.dist;
        }
    }

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};
    static int N, M, F;
    static int[][] map;
    static List<Guest> guests = new ArrayList<>();
    static boolean[][] visited;
    static int[][] guestIdx; // 손님이 있으면 인덱스, 없으면 -1

    static int getDist(int sy, int sx, int dy, int dx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sy, sx, 0});
        visited = new boolean[N][N];
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int dist = cur[2];

            if (y == dy && x == dx) return dist;
            
            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N ||
                    map[ny][nx] == 1 || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.offer(new int[] {ny, nx, dist + 1});
            }
        }
        
        return -1;
    }

    static int updateDist(int ty, int tx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {ty, tx, 0});
        visited = new boolean[N][N];
        visited[ty][tx] = true;
        int cnt = guests.size();
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int dist = cur[2];

            if (guestIdx[y][x] != -1) {
                guests.get(guestIdx[y][x]).dist = dist;
                guestIdx[y][x] = -1;
                cnt--;
            }

            if (cnt == 0) break;
            
            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N ||
                    map[ny][nx] == 1 || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.offer(new int[] {ny, nx, dist + 1});
            }
        }

        return cnt;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        guestIdx = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(guestIdx[i], -1);
        }

        st = new StringTokenizer(br.readLine());
        int ty = Integer.parseInt(st.nextToken()) - 1;
        int tx = Integer.parseInt(st.nextToken()) - 1;
        
        for (int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int dy = Integer.parseInt(st.nextToken()) - 1;
            int dx = Integer.parseInt(st.nextToken()) - 1;
            guests.add(new Guest(sy, sx, dy, dx, 0));
        }

        // 손님 태우기
        while (!guests.isEmpty()) {
            for (int i = 0; i < guests.size(); i++) {
                Guest g = guests.get(i);
                guestIdx[g.sy][g.sx] = i;
            }
            
            if (updateDist(ty, tx) > 0) {
                F = -1;
                break;
            };
            
            Collections.sort(guests);
            
            Guest guest = guests.get(0);
            F -= guest.dist;
            
            int gdDist = getDist(guest.sy, guest.sx, guest.dy, guest.dx);

            if (gdDist == -1 || F - gdDist < 0) {
                F = -1;
                break;
            }

            F += gdDist;
            ty = guest.dy;
            tx = guest.dx;

            guests.remove(0);
        }
        
        System.out.println(F);
    }
}