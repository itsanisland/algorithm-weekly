import java.util.*;
import java.io.*;

class Main {

    static int N, L;
    static int[][] board;

    static boolean check(int[] line) {
        boolean[] used = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) continue;
            else if (line[i] - line[i + 1] == 1) { // 내려가는 경사 (\)
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || used[j] || line[i + 1] != line[j]) return false;
                    used[j] = true;
                }
                i += L - 1;
            } else if (line[i] - line[i + 1] == -1) { // 올라가는 경사 (/)
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || used[j] || line[i] != line[j]) return false;
                    used[j] = true;
                }
            } else return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (check(board[i])) ans++;
            
            int[] cLine = new int[N];
            for (int j = 0; j < N; j++) cLine[j] = board[j][i];
            if(check(cLine)) ans++;
        }
        System.out.println(ans);
    }
}