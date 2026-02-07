import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {0, 1, 0, -1};
    static final int[] DX = {-1, 0, 1, 0};
    
    static final int[][] DSY = {
        {-1, 1, -1, 1, -1, 1, -2, 2, 0},    // 왼쪽 이동 기준
        {0, 0, 1, 1, -1, -1, 0, 0, 2},      // 아래
        {-1, 1, -1, 1, -1, 1, -2, 2, 0},    // 오른쪽
        {0, 0, -1, -1, 1, 1, 0, 0, -2}      // 위
    };
    static final int[][] DSX = {
        {0, 0, -1, -1, 1, 1, 0, 0, -2},     // 왼쪽 이동 기준
        {-1, 1, -1, 1, -1, 1, -2, 2, 0},
        {0, 0, 1, 1, -1, -1, 0, 0, 2},
        {-1, 1, -1, 1, -1, 1, -2, 2, 0}
    };
    
    static final int[] ratio = {7, 7, 10, 10, 1, 1, 2, 2, 5};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] A = new int[N][N];
        int[][] B = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int y = N / 2, x = N / 2;

        // 격자 밖으로 나간 모래 양
        int ans = 0;
        int l = 1;
        int d = 0;
        boolean finished = false;

        // 토네이도
        while (!finished) {
            for (int k = 0; k < 2; k++) {
                if (finished) break;
                
                for (int i = 0; i < l; i++) {
                    if (y == 0 && x == 0) {
                        finished = true;
                        break;
                    }
                    
                    int ny = y + DY[d];
                    int nx = x + DX[d];

                    int movedSand = 0;
                    for (int j = 0; j < 9; j++) {
                        int sy = ny + DSY[d][j];
                        int sx = nx + DSX[d][j];
                        int sand = A[ny][nx] * ratio[j] / 100;
    
                        if (sy < 0 || sy >= N || sx < 0 || sx >= N) {
                            ans += sand;
                        } else {
                            A[sy][sx] += sand;
                        }

                        movedSand += sand;
                    }
    
                    int leftSand = A[ny][nx] - movedSand;
                    int nny = ny + DY[d];
                    int nnx = nx + DX[d];
    
                    if (nny < 0 || nny >= N || nnx < 0 || nnx >= N) {
                        ans += leftSand;
                    } else {
                        A[nny][nnx] += leftSand;
                    }

                    A[ny][nx] = 0;
    
                    y = ny;
                    x = nx;
                }
                
                d = (d + 1) % 4;
            }
            
            l++;
        }
        
        System.out.println(ans);
    }
}