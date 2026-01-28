import java.util.*;
import java.io.*;

class Main {

    static int N, S, ans = 40_000;
    static int[][] map;
    static int[][] boundary;

    static void separate() {
        // (x, y) 조합
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                // (d1, d2) 조합
                for (int d1 = 1; d1 < y; d1++) {
                    for (int d2 = 1; d2 <= N - y; d2++) {
                        if (x + d1 + d2 <= N) {
                            // setBoundary(x, y, d1, d2);
                            calcSum(x, y, d1, d2);
                        }
                    }
                }
            }
        }
    }

    // 디버깅용
    static void setBoundary(int x, int y, int d1, int d2) {
        boundary = new int[N + 1][N + 1];
        
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (1 <= r && r < x + d1 && 1 <= c && c <= y && r + c < x + y) boundary[r][c] = 1;
                else if (1 <= r && r <= x + d2 && y < c && c <= N && r - c < x - y) boundary[r][c] = 2;
                else if (x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2 && r - c > (x + d1) - (y - d1)) boundary[r][c] = 3;
                else if (x + d2 < r && r <= N && y - d1 + d2 <= c && c <= N && r + c > (x + d2) + (y + d2)) boundary[r][c] = 4;
                else boundary[r][c] = 5;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(boundary[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void calcSum(int x, int y, int d1, int d2) {
        int[] p = new int[5];
        int b = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (1 <= r && r < x + d1 && 1 <= c && c <= y && r + c < x + y) p[0] += map[r][c];
                else if (1 <= r && r <= x + d2 && y < c && c <= N && r - c < x - y) p[1] += map[r][c];
                else if (x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2 && r - c > (x + d1) - (y - d1)) p[2] += map[r][c];
                else if (x + d2 < r && r <= N && y - d1 + d2 <= c && c <= N && r + c > (x + d2) + (y + d2)) p[3] += map[r][c];
                else p[4] += map[r][c];
            }
        }

        int min = 40_000;
        int max = 0;
        for (int num : p) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        ans = Math.min(ans, max - min);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                S += map[i][j];
            }
        }

        separate();
        
        System.out.println(ans);
    }
}