import java.util.*;
import java.io.*;

class Main {

    public static int N, M;
    public static int[][] A, B;

    public static void flip(int y, int x) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                A[y + i][x + j] ^= 1; // 토글
            }
        }
    }

    public static boolean isSame() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        B = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                B[i][j] = s.charAt(j) - '0';
            }
        }

        int cnt = 0;
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                if (A[i][j] != B[i][j]) {
                    flip(i, j);
                    cnt++;
                }
            }
        }
        
        System.out.println(isSame() ? cnt : -1);
    }
}