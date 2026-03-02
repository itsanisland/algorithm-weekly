import java.util.*;
import java.io.*;

class Main {

    static List<Integer> getBorder(int[][] board, int n, int ith) {
        List<Integer> ret = new ArrayList<>();

        int d = n / 2 - ith;

        for (int x = ith; x < n - ith - 1; x += d) ret.add(board[ith][x]);
        for (int y = ith; y < n - ith - 1; y += d) ret.add(board[y][n - ith - 1]);
        for (int x = n - ith - 1; x > ith; x -= d) ret.add(board[n - ith - 1][x]);
        for (int y = n - ith - 1; y > ith; y -= d) ret.add(board[y][ith]);
        
        return ret;
    }

    static void restore(int[][] board, List<Integer> line, int n, int ith) {
        int d = n / 2 - ith;
        int i = 0;

        for (int x = ith; x < n - ith - 1; x += d) board[ith][x] = line.get(i++);
        for (int y = ith; y < n - ith - 1; y += d) board[y][n - ith - 1] = line.get(i++);
        for (int x = n - ith - 1; x > ith; x -= d) board[n - ith - 1][x] = line.get(i++);
        for (int y = n - ith - 1; y > ith; y -= d) board[y][ith] = line.get(i++);
    }

    static int[][] rotate(int[][] board, int n, int d) {
        // 1. d를 시계방향 양수 값으로 보정 (예: -45 -> 315)
        if (d < 0) d += 360;
        int rotateCnt = d / 45;
        int lineCnt = n / 2;
    
        for (int i = 0; i < lineCnt; i++) {
            List<Integer> line = getBorder(board, n, i);

            Collections.rotate(line, rotateCnt);

            restore(board, line, n, i);
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