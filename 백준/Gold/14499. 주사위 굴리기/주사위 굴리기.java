import java.util.*;
import java.io.*;

class Main {

    private final static int[] DY = {0, 0, 0, -1, 1};
    private final static int[] DX = {0, 1, -1, 0, 0};

    // 각 주사위 면에 적힌 숫자
    // 인덱스 의미 -> 1: 위, 2: 북, 3: 서, 4: 동, 5: 남, 6: 아래
    private final static int[] D = new int[7];

    public static void roll(int dir) {
        int tmp = D[1];
        if (dir == 1) { // 동
            // 위 -> 동 -> 아래 -> 서 -> 위
            D[1] = D[3];
            D[3] = D[6];
            D[6] = D[4];
            D[4] = tmp;
        } else if (dir == 2) { // 서
            // 위 -> 서 -> 아래 -> 동 -> 위
            D[1] = D[4];
            D[4] = D[6];
            D[6] = D[3];
            D[3] = tmp;
        } else if (dir == 3) { // 북
            // 위 -> 북 -> 아래 -> 남 -> 위
            D[1] = D[5];
            D[5] = D[6];
            D[6] = D[2];
            D[2] = tmp;
        } else { // 남
            // 위 -> 남 -> 아래 -> 북 -> 위
            D[1] = D[2];
            D[2] = D[6];
            D[6] = D[5];
            D[5] = tmp;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());

            int ny = y + DY[dir];
            int nx = x + DX[dir];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

            roll(dir);
            
            if (map[ny][nx] == 0) {
                map[ny][nx] = D[6];
            } else {
                D[6] = map[ny][nx];
                map[ny][nx] = 0;
            }
            
            y = ny;
            x = nx;
            
            System.out.println(D[1]);
        }
    }
}