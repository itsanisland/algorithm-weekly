import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DDY = {-1, -1, 1, 1};
    static final int[] DDX = {-1, 1, -1, 1};

    static int N;
    static int[][] water;
    static boolean[][] cloud;
    static boolean[][] movingCloud;

    static void moveCloud(int d, int s) {
        movingCloud = new boolean[N][N];
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!cloud[y][x]) continue;

                int move = s % N;
                int ny = (y + DY[d] * move + N) % N;
                int nx = (x + DX[d] * move + N) % N;
                
                movingCloud[ny][nx] = true;
                water[ny][nx]++;
                cloud[y][x] = false;
            }
        }
    }
    
    static void copyWater() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!movingCloud[y][x]) continue;
                
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = y + DDY[d];
                    int nx = x + DDX[d];
        
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N || water[ny][nx] == 0) continue;
                    cnt++;
                }
                water[y][x] += cnt;
            }
        }
    }

    static void setCloud() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (water[y][x] >= 2 && !movingCloud[y][x]) {
                    cloud[y][x] = true;
                    water[y][x] -= 2;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        water = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloud = new boolean[N][N];
        cloud[N - 1][0] = true;
        cloud[N - 1][1] = true;
        cloud[N - 2][0] = true;
        cloud[N - 2][1] = true;
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            moveCloud(d, s);
            copyWater();
            setCloud();
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += water[i][j];
            }
        }
        
        System.out.println(ans);
    }
}