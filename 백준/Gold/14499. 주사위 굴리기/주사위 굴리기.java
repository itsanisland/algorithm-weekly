import java.io.*;
import java.util.*;

public class Main {

    public final static int[] DY = {0, 0, 0, -1, 1};
    public final static int[] DX = {0, 1, -1, 0, 0};
    
    public static int[] dice = new int[6];

    public static void roll(int d) {
        int tmp;
        switch (d) {
            case 1:
                tmp = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = tmp;
                break;
            case 2:
                tmp = dice[5];
                dice[5] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = tmp;
                break;
            case 3:
                tmp = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = tmp;
                break;
            case 4:
                tmp = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = tmp;
                break;
        }
    }
    
    public static void main(String[] args) throws Exception {
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
            int d = Integer.parseInt(st.nextToken());

            int ny = y + DY[d];
            int nx = x + DX[d];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

            roll(d);

            if (map[ny][nx] == 0) {
                map[ny][nx] = dice[5];
            } else {
                dice[5] = map[ny][nx];
                map[ny][nx] = 0;
            }

            System.out.println(dice[0]);

            y = ny;
            x = nx;
        }
    }
}