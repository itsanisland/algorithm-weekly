import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = {-1, 1, 0, 0};
    public final static int[] DX = {0, 0, -1, 1};

    public static int N, M;
    public static int[][] map;
    public static List<Integer> purifier = new ArrayList<>();


    public static void move(int sy, int sx, int dy, int dx, int d) {
        while (!(dy == sy && dx == sx)) {
            map[dy][dx] = map[dy - DY[d]][dx - DX[d]];
            dy -= DY[d];
            dx -= DX[d];
        }
    }
    
    public static void purify() {
        int upY = purifier.get(0);
        int downY = purifier.get(1);

        move(0, 0, upY - 1, 0, 1);
        move(0, M - 1, 0, 0, 2);
        move(upY, M - 1, 0, M - 1, 0);
        move(upY, 1, upY, M - 1, 3);

        move(N - 1, 0, downY + 1, 0, 0);
        move(N - 1, M - 1, N - 1, 0, 2);
        move(downY, M - 1, N - 1, M - 1, 1);
        move(downY, 1, downY, M - 1, 3);

        map[upY][1] = 0;
        map[downY][1] = 0;
    }

    public static void spread() {
        int[][] temp = new int[N][M];
    
        // 공기청정기 위치 유지
        for (int y : purifier) {
            temp[y][0] = -1;
        }
    
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] <= 0) continue;
    
                int dust = map[y][x] / 5;
                int cnt = 0;
    
                for (int d = 0; d < 4; d++) {
                    int ny = y + DY[d];
                    int nx = x + DX[d];
    
                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    if (map[ny][nx] == -1) continue;
    
                    temp[ny][nx] += dust;
                    cnt++;
                }
    
                temp[y][x] += map[y][x] - dust * cnt;
            }
        }
    
        map = temp;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    purifier.add(i);
                }
            }
        }

        while (T-- > 0) {
            spread();
            purify();
        }

        int ans = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans += map[i][j];
            }
        }
        
        System.out.println(ans);
    }
}