import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { 0, 1, 0, -1 }; // 시계 방향 기준
    public final static int[] DX = { 1, 0, -1, 0 };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        char[][] B = new char[N + 2][N + 2];
        int[][] D = new int[N + 2][N + 2]; // 방향
        
        Arrays.fill(B[0], '#');
        Arrays.fill(B[N + 1], '#');
        for (int i = 1; i < N + 1; i++) {
            B[i][0] = '#';
            for (int j = 1; j < N + 1; j++) {
                B[i][j] = '.';
            }
            B[i][N + 1] = '#';
        }
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            B[r][c] = 'A';
        }

        int L = Integer.parseInt(br.readLine());
        char[] C = new char[10_001];
        
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            C[x] = c;
        }

        int sec = 0;
        int hy = 1, hx = 1; // 머리
        B[hy][hx] = 'S';
        int dir = 0; // 현재 머리 방향(오른쪽)
        int ty = 1, tx = 1; // 꼬리
        
        while (true) {
            if (C[sec] == 'D') {
                dir++;
                dir %= 4;
            } else if (C[sec] == 'L') {
                dir += 3;
                dir %= 4;
            }
            D[hy][hx] = dir;

            hy += DY[dir]; hx += DX[dir];

            sec++;

            if (B[hy][hx] == '#' || B[hy][hx] == 'S') {
                break;
            }

            if (B[hy][hx] == '.') {
                B[ty][tx] = '.';
                int dt = D[ty][tx];
                ty += DY[dt]; tx += DX[dt];
            }
            
            B[hy][hx] = 'S';
        }

        System.out.println(sec);
    }  
}