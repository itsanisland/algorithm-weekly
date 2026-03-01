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

    static List<Integer> getBorder(int ith) {
        List<Integer> line = new ArrayList<>();
        for (int x = ith; x < M - ith - 1; x++) line.add(board[ith][x]);
        for (int y = ith; y < N - ith - 1; y++) line.add(board[y][M - ith - 1]);
        for (int x = M - ith - 1; x > ith; x--) line.add(board[N - ith - 1][x]);
        for (int y = N - ith - 1; y > ith; y--) line.add(board[y][ith]);
        return line;
    }

    static void restore(List<Integer> line, int ith) {
        int i = 0;
        for (int x = ith; x < M - ith - 1; x++) board[ith][x] = line.get(i++);
        for (int y = ith; y < N - ith - 1; y++) board[y][M - ith - 1] = line.get(i++);
        for (int x = M - ith - 1; x > ith; x--) board[N - ith - 1][x] = line.get(i++);
        for (int y = N - ith - 1; y > ith; y--) board[y][ith] = line.get(i++);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int lineCnt = Math.min(N, M) / 2;
        for (int i = 0; i < lineCnt; i++) {
            // 테두리 추출
            List<Integer> line = getBorder(i);

            // 실제 회전 횟수 계산
            int rotateCnt = R % line.size();

            // 회전
            Collections.rotate(line, -rotateCnt); // 반시계 방향

            // 복원
            restore(line, i);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(board[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}