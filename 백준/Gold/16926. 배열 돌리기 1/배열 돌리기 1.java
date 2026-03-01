import java.util.*;
import java.io.*;

class Main {

    static class Pair {
        int y, x;
        Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N, M;
    static int[][] board;

    static void rotate() {
        Pair start = new Pair(0, 0);
        Pair end = new Pair(N - 1, M - 1);
        
        while (start.x < end.x && start.y < end.y) {
            int temp = board[start.y][start.x];
            
            for (int i = start.x; i < end.x; i++) board[start.y][i] = board[start.y][i + 1];
            for (int i = start.y; i < end.y; i++) board[i][end.x] = board[i + 1][end.x];
            for (int i = end.x; i > start.x; i--) board[end.y][i] = board[end.y][i - 1];
            for (int i = end.y; i > start.y; i--) board[i][start.x] = board[i - 1][start.x];

            board[start.y + 1][start.x] = temp;
            
            start.x++; start.y++;
            end.x--; end.y--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        while (K-- > 0) {
            rotate();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }       
    }
}