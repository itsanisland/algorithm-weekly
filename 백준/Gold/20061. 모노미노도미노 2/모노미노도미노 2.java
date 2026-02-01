import java.util.*;
import java.io.*;

class Main {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean[][] board = new boolean[10][10];
    static int score;

    static void moveBlue(List<Pair> block) {
        // y 이동(->)
        boolean canMove = true;
        while (canMove) {
            for (Pair b : block) {
                if (b.y + 1 > 9 || board[b.x][b.y + 1]) {
                    canMove = false;
                    break;
                }
            }

            if (canMove) {
                for (Pair b : block) b.y += 1;
            }
        }
        
        for (Pair b : block) {
            board[b.x][b.y] = true;
        }

        // 열 가득 찼을 경우 -> 점수 획득 + 열 비우기 + 오른쪽으로 이동
        boolean removed;
        do {
            removed = false;
            for (int i = 6; i < 10; i++) {
                int cnt = 0;
                for (int j = 0; j < 4; j++) {
                    if (board[j][i]) cnt++;
                }
                if (cnt == 4) {
                    score++;
                    removed = true;
        
                    // 삭제
                    for (int j = 0; j < 4; j++) board[j][i] = false;
        
                    // 밀기
                    for (int col = i; col > 4; col--) {
                        for (int row = 0; row < 4; row++) {
                            board[row][col] = board[row][col - 1];
                        }
                    }
                    for (int row = 0; row < 4; row++) board[row][4] = false;
                    break; // 다시 검사
                }
            }
        } while (removed);

        // 연한 부분 확인 -> 블록이 있는 열의 개수만큼 모든 타일을 오른쪽으로 이동
        int moveCnt = 0;
        for (int i = 4; i < 6; i++) {
            boolean move = false;
            for (int j = 0; j < 4; j++) {
                if (board[j][i]) {
                    moveCnt++;
                    break;
                }
            }
        }

        for (int r = 0; r < 4; r++) {
            for (int t = 0; t < moveCnt; t++) {
                for (int c = 9; c > 4; c--) {
                    board[r][c] = board[r][c - 1];
                }
            }
            board[r][4] = false;
            board[r][5] = false;
        }
    }

    static void moveGreen(List<Pair> block) {
        // x 이동(아래)
        boolean canMove = true;
        while (canMove) {
            for (Pair b : block) {
                if (b.x + 1 > 9 || board[b.x + 1][b.y]) {
                    canMove = false;
                    break;
                }
            }

            if (canMove) {
                for (Pair b : block) b.x += 1;
            }
        }
        
        for (Pair b : block) {
            board[b.x][b.y] = true;
        }

        // 행 가득 찼을 경우 -> 점수 획득 + 행 비우기
        boolean removed;
        do {
            removed = false;
            for (int i = 6; i < 10; i++) {
                int cnt = 0;
                for (int j = 0; j < 4; j++) {
                    if (board[i][j]) cnt++;
                }
                if (cnt == 4) {
                    score++;
                    removed = true;
        
                    // 삭제
                    for (int j = 0; j < 4; j++) board[i][j] = false;
        
                    // 밀기
                    // i 행이 삭제된 상태
                    for (int row = i; row > 4; row--) {
                        for (int col = 0; col < 4; col++) {
                            board[row][col] = board[row - 1][col];
                        }
                    }
                    
                    // 가장 위 행 비우기
                    for (int col = 0; col < 4; col++) board[4][col] = false;
                    break; // 다시 검사
                }
            }
        } while (removed);

        // 연한 부분 확인 -> 블록이 있는 행의 개수만큼 아래쪽으로 이동
        int moveCnt = 0;
        for (int i = 4; i < 6; i++) {
            boolean move = false;
            for (int j = 0; j < 4; j++) {
                if (board[i][j]) {
                    moveCnt++;
                    break;
                }
            }
        }

        for (int c = 0; c < 4; c++) {
            for (int t = 0; t < moveCnt; t++) {
                for (int r = 9; r > 4; r--) {
                    board[r][c] = board[r - 1][c];
                }
            }
            board[4][c] = false;
            board[5][c] = false;
        }
    }

    static int countTiles() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 6; j < 10; j++) {
                if (board[i][j]) cnt++;
                if (board[j][i]) cnt++;
            }
        }
        return cnt;
    }

    static void print() {
        for (int i = -1; i < 10; i++) {
            System.out.print((i == -1 ? " " : i) + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                if (i > 3 && j > 3) continue;
                System.out.print((board[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            List<Pair> blockB = new ArrayList<>();
            List<Pair> blockG = new ArrayList<>();
            
            blockB.add(new Pair(x, y));
            blockG.add(new Pair(x, y));
            
            if (t == 2) {
                blockB.add(new Pair(x, y + 1));
                blockG.add(new Pair(x, y + 1));
            } else if (t == 3) {
                blockB.add(new Pair(x + 1, y));
                blockG.add(new Pair(x + 1, y));
            }

            moveBlue(blockB);
            moveGreen(blockG);

            // print();
        }

        System.out.println(score);
        System.out.println(countTiles());
    }
}