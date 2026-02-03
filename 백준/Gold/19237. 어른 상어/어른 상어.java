import java.util.*;
import java.io.*;

class Main {

    static class Smell {
        int shark, t = 0;
    }

    static final int[] DY = {0, -1, 1, 0, 0};
    static final int[] DX = {0, 0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];
        Smell[][] smells = new Smell[N][N];
        int[] directions = new int[M + 1];
        int[][][] priorities= new int[M + 1][5][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                smells[i][j] = new Smell();
                if (board[i][j] > 0) {
                    smells[i][j].shark = board[i][j];
                    smells[i][j].t = K;
                }
            }
        }
  
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            directions[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    priorities[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int t = 0;
        
        while (++t <= 1000) {
            int[][] moved = new int[N][N];

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (board[y][x] == 0) continue;

                    int shark = board[y][x];
                    int d = directions[shark];

                    boolean isMoved = false;

                    // 냄새 없는 칸 탐색
                    for (int i = 0; i < 4; i++) {
                        int nd = priorities[shark][d][i];
                        int ny = y + DY[nd];
                        int nx = x + DX[nd];
                        
                        if (ny < 0 || ny >= N || nx < 0 || nx >= N || smells[ny][nx].shark > 0) continue;
                        
                        if (moved[ny][nx] == 0 || moved[ny][nx] > shark) {
                            moved[ny][nx] = shark;
                        }

                        directions[shark] = nd;
                        isMoved = true;
                        break;
                    }
                         
                    // 없으면 자기 냄새 탐색
                    if (!isMoved) {
                        for (int i = 0; i < 4; i++) {
                            int nd = priorities[shark][d][i];
                            int ny = y + DY[nd];
                            int nx = x + DX[nd];
                            
                            if (ny < 0 || ny >= N || nx < 0 || nx >= N || smells[ny][nx].shark != shark) continue;
                            
                            if (moved[ny][nx] == 0 || moved[ny][nx] > shark) {
                                moved[ny][nx] = shark;
                            }

                            directions[shark] = nd;
                            break;
                        }
                    }
                }
            }

            board = moved;
           
            // 모든 냄새 감소
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (smells[i][j].t > 0) {
                        smells[i][j].t--;
                        if (smells[i][j].t == 0) {
                            smells[i][j].shark = 0;
                        }
                    }
                }
            }

            // 이동 완료 후 냄새 남기기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] > 0) {
                        smells[i][j].shark = board[i][j];
                        smells[i][j].t = K;
                    }
                }
            }

            boolean onlyOne = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] > 1) {
                        onlyOne = false;
                    }
                }
            }
            if (onlyOne) break;
        }
        
        System.out.println(t > 1000 ? -1 : t);
    }
}