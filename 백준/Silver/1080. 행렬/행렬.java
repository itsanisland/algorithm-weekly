import java.util.*;
import java.io.*;

class Main {

    public static int N, M;
    public static boolean[][] A, B;

    public static void reverse(int y, int x) {
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                A[i][j] = !A[i][j];
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
        A = new boolean[N][M];
        B = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j] = s.charAt(j) == '1';
            }
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                B[i][j] = s.charAt(j) == '1';
            }
        }

        if ((N < 3 || M < 3) && !isSame()) {
            System.out.println(-1);
            return;
        }

        int cnt = 0;
        boolean flag = false;
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                if (A[i][j] != B[i][j]) {
                    reverse(i, j);
                    cnt++;
                }
            }
        }
        
        System.out.println(isSame() ? cnt : -1);
    }
}