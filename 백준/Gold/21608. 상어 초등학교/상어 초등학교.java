import java.util.*;
import java.io.*;

class Main {

    static class Pos implements Comparable<Pos> {
        int r, c, favCnt, emptyCnt;

        Pos(int r, int c, int favCnt, int emptyCnt) {
            this.r = r;
            this.c = c;
            this.favCnt = favCnt;
            this.emptyCnt = emptyCnt;
        }

        public int compareTo(Pos o) {
            if (favCnt == o.favCnt) {
                if (emptyCnt == o.emptyCnt) {
                    if (r == o.r) return c - o.c;
                    return r - o.r;
                }
                return o.emptyCnt - emptyCnt;
            }
            return o.favCnt - favCnt;
        }
    }

    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    static int N;
    static int[][] board, fav;

    static void setPos(int std) {
        Pos pos = new Pos(-1, -1, -1, -1);

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] > 0) continue;

                int favCnt = 0;
                int emptyCnt = 0;
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + DR[d];
                    int nc = c + DC[d];
        
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    if (board[nr][nc] == 0) emptyCnt++;
                    else {
                        for (int j = 0; j < 4; j++) {
                            if (board[nr][nc] == fav[std][j]) favCnt++;
                        }
                    }
                }

                Pos newPos = new Pos(r, c, favCnt, emptyCnt);
                if (pos.compareTo(newPos) > 0) pos = newPos;
            }
        }

        board[pos.r][pos.c] = std;
    }

    static int getScore() {
        int score = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int std = board[r][c];
                int favCnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = r + DR[d];
                    int nc = c + DC[d];
        
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    else {
                        for (int j = 0; j < 4; j++) {
                            if (board[nr][nc] == fav[std][j]) favCnt++;
                        }
                    }
                }

                score += favCnt == 0 ? 0 : Math.pow(10, favCnt - 1);
            }
        }

        return score;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        int SIZE = N * N;
        fav = new int[SIZE + 1][4];

        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int std = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                fav[std][j] = Integer.parseInt(st.nextToken()); 
            }

            setPos(std);
        }
        
        System.out.println(getScore());
    }
}