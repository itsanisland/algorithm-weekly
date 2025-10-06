import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        // String[] -> char[][]
        char[][] map =  new char[m][n];
        boolean[][] del = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        
        while (true) {
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char ch = map[i][j];

                    if (ch == 'x') {
                        continue;
                    }

                    char down = map[i + 1][j];
                    char next = map[i][j + 1];
                    char diag = map[i + 1][j + 1];

                    if (ch == down && ch == next && ch == diag) {
                        del[i][j] = true;
                        del[i + 1][j] = true;
                        del[i][j + 1] = true;
                        del[i + 1][j + 1] = true;
                    }
                }
            }
            
            int cnt = 0;
            
            // 1) 표시된 칸 일괄 제거 + 개수 집계
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (del[i][j] && map[i][j] != 'x') {
                        map[i][j] = 'x';
                        cnt++;
                    }
                }
            }
            
            // 2) 열 단위 패킹(중력)
            for (int c = 0; c < n; c++) {
                int write = m - 1;
                for (int r = m - 1; r >= 0; r--) {
                    if (map[r][c] != 'x') {
                        map[write][c] = map[r][c];
                        if (write != r) {
                            map[r][c] = 'x';
                        }
                        write--;
                    }
                }
            }
            
            for (int i = 0; i < m; i++) {
                Arrays.fill(del[i], false);
            }

            if (cnt == 0) {
                break;
            }
            
            answer += cnt;
        }
        
        return answer;
    }
}