import java.io.*;
import java.util.*;

public class Main {

    static class Smell {
        int shark, time;
        Smell(int shark, int time) {
            this.shark = shark;
            this.time = time;
        }
    }

    static int N, M, K;
    static int[][] board;
    static Smell[][] smell;
    static int[] dir;
    static int[][][] priority;

    static final int[] dy = {0, -1, 1, 0, 0};
    static final int[] dx = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        smell = new Smell[N][N];
        dir = new int[M + 1];
        priority = new int[M + 1][5][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > 0) {
                    smell[i][j] = new Smell(board[i][j], K);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) dir[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= M; i++) {
            for (int d = 1; d <= 4; d++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    priority[i][d][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int time = 1;

        while (time <= 1000) {
            int[][] next = new int[N][N];

            // 1) 이동
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (board[y][x] == 0) continue;

                    int shark = board[y][x];
                    int d = dir[shark];
                    boolean moved = false;

                    // 냄새 없는 칸
                    for (int k = 0; k < 4; k++) {
                        int nd = priority[shark][d][k];
                        int ny = y + dy[nd];
                        int nx = x + dx[nd];

                        if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                        if (smell[ny][nx] != null) continue;

                        move(next, shark, ny, nx);
                        dir[shark] = nd;
                        moved = true;
                        break;
                    }

                    // 자기 냄새
                    if (!moved) {
                        for (int k = 0; k < 4; k++) {
                            int nd = priority[shark][d][k];
                            int ny = y + dy[nd];
                            int nx = x + dx[nd];

                            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                            if (smell[ny][nx].shark != shark) continue;

                            move(next, shark, ny, nx);
                            dir[shark] = nd;
                            break;
                        }
                    }
                }
            }

            board = next;

            // 2) 냄새 감소
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (smell[i][j] != null) {
                        smell[i][j].time--;
                        if (smell[i][j].time == 0) smell[i][j] = null;
                    }
                }
            }

            // 3) 새 냄새 생성
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] > 0) {
                        smell[i][j] = new Smell(board[i][j], K);
                    }
                }
            }

            // 종료
            boolean onlyOne = true;
            for (int i = 2; i <= M; i++) {
                if (exists(i)) {
                    onlyOne = false;
                    break;
                }
            }

            if (onlyOne) {
                System.out.println(time);
                return;
            }

            time++;
        }

        System.out.println(-1);
    }

    static void move(int[][] next, int shark, int y, int x) {
        if (next[y][x] == 0 || next[y][x] > shark) {
            next[y][x] = shark;
        }
    }

    static boolean exists(int s) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j] == s) return true;
        return false;
    }
}