import java.util.*;
import java.io.*;

class Main {

    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static final int[] DX = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};
    // 상:0 좌:1 하:2 우:3 (문제 기준 순서)
    static final int[] DSX = {0, -1, 0, 1, 0};
    static final int[] DSY = {0, 0, -1, 0, 1};

    static int[][] smell = new int[4][4];   // 냄새 전용
    static boolean[][] visited;

    static List<Integer>[] fish = new ArrayList[16];
    static List<Integer>[] added = new ArrayList[16];

    static String sharkMove = "444";
    static int maxEatenFish = 0;

    static void moveFish(int pos, int idx, int sx, int sy) {
        int x = pos / 4;
        int y = pos % 4;
        int d = fish[pos].get(idx);

        for (int i = 0; i < 8; i++) {
            int nx = x + DX[d];
            int ny = y + DY[d];

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4
                    || smell[nx][ny] > 0
                    || (nx == sx && ny == sy)) {

                d = (d - 1 + 8) % 8;
                continue;
            }

            int newPos = nx * 4 + ny;
            added[newPos].add(d);
            return;
        }

        added[pos].add(d);
    }

    static void moveShark(int x, int y, int eatenFish, String move) {
        if (move.length() == 3) {
            if (maxEatenFish < eatenFish ||
               (maxEatenFish == eatenFish && move.compareTo(sharkMove) < 0)) {
                maxEatenFish = eatenFish;
                sharkMove = move;
            }
            return;
        }
    
        for (int d = 1; d <= 4; d++) {
            int nx = x + DSX[d];
            int ny = y + DSY[d];
    
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
    
            int pos = nx * 4 + ny;
            boolean firstVisit = !visited[nx][ny];
    
            if (firstVisit) visited[nx][ny] = true;
    
            moveShark(nx, ny,
                      eatenFish + (firstVisit ? fish[pos].size() : 0),
                      move + d);
    
            if (firstVisit) visited[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 16; i++) fish[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            fish[fx * 4 + fy].add(d);
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;

        while (S-- > 0) {
            // 1. 복제 마법 시전 (복사만)
            List<Integer>[] cpFish = new ArrayList[16];
            for (int i = 0; i < 16; i++) {
                cpFish[i] = new ArrayList<>(fish[i]);
            }

            // 2. 물고기 이동
            for (int i = 0; i < 16; i++) added[i] = new ArrayList<>();

            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < fish[i].size(); j++) {
                    moveFish(i, j, sx, sy);
                }
            }

            for (int i = 0; i < 16; i++) {
                fish[i] = added[i];
            }

            // 3. 냄새 감소 (상어 이동 전에!)
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (smell[i][j] > 0) smell[i][j]--;
                }
            }

            // 4. 상어 이동
            visited = new boolean[4][4];
            sharkMove = "444";
            maxEatenFish = 0;

            moveShark(sx, sy, 0, "");

            for (int i = 0; i < 3; i++) {
                int d = sharkMove.charAt(i) - '0';
                sx += DSX[d];
                sy += DSY[d];

                int pos = sx * 4 + sy;

                if (!fish[pos].isEmpty()) {
                    fish[pos].clear();
                    smell[sx][sy] = 2;  // 냄새 2턴 유지
                }
            }

            // 5. 복제 완료
            for (int i = 0; i < 16; i++) {
                fish[i].addAll(cpFish[i]);
            }
        }

        int ans = 0;
        for (int i = 0; i < 16; i++) {
            ans += fish[i].size();
        }

        System.out.println(ans);
    }
}