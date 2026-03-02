import java.util.*;
import java.io.*;

class Main {

    static int[][] rotate(int[][] board, int n, int d) {
        // 1. d를 시계방향 양수 값으로 보정 (예: -45 -> 315)
        if (d < 0) d += 360;
        int rotateCnt = d / 45;
        int mid = (n - 1) / 2;
    
        while (rotateCnt-- > 0) {
            int[][] nextBoard = new int[n][n];
            
            // 일단 전체 복사 (회전하지 않는 칸들을 위해)
            for (int i = 0; i < n; i++) {
                nextBoard[i] = board[i].clone();
            }
    
            for (int i = 0; i < n; i++) {
                // 주 대각선 -> 가운데 열
                nextBoard[i][mid] = board[i][i];
                // 가운데 열 -> 부 대각선
                nextBoard[i][n - 1 - i] = board[i][mid];
                // 부 대각선 -> 가운데 행
                nextBoard[mid][n - 1 - i] = board[i][n - 1 - i];
                // 가운데 행 -> 주 대각선
                nextBoard[i][i] = board[mid][i];
            }
            // 다음 회전을 위해 원본 갱신
            board = nextBoard;
        }
        
        return board;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int[][] board = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            board = rotate(board, n, d);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bw.write(board[i][j] + " ");
                }
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }
}