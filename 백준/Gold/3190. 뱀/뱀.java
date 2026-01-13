import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {0, 1, 0, -1};
    static final int[] DX = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] board = new int[N + 1][N + 1]; // 0: 빈칸, 1: 사과
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        char[] turn = new char[10001];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            turn[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
        }

        Deque<int[]> snake = new ArrayDeque<>();
        boolean[][] body = new boolean[N + 1][N + 1];

        snake.offerFirst(new int[]{1, 1});
        body[1][1] = true;

        int y = 1, x = 1, dir = 0, time = 0;

        while (true) {
            time++;

            int ny = y + DY[dir];
            int nx = x + DX[dir];

            // 충돌
            if (ny < 1 || ny > N || nx < 1 || nx > N || body[ny][nx]) {
                break;
            }

            // 머리 이동
            snake.offerFirst(new int[]{ny, nx});
            body[ny][nx] = true;

            // 사과가 없으면 꼬리 제거
            if (board[ny][nx] == 0) {
                int[] tail = snake.pollLast();
                body[tail[0]][tail[1]] = false;
            } else {
                board[ny][nx] = 0;
            }

            // 방향 전환
            if (turn[time] == 'D') dir = (dir + 1) % 4;
            else if (turn[time] == 'L') dir = (dir + 3) % 4;

            y = ny;
            x = nx;
        }

        System.out.println(time);
    }
}