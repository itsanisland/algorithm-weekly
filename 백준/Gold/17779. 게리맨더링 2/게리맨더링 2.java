import java.io.*;
import java.util.*;

class Main {

    static int N, total, ans = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        // x, y는 유효 범위만
        for (int x = 1; x <= N - 2; x++) {
            for (int y = 2; y <= N - 1; y++) {
                for (int d1 = 1; d1 < y; d1++) {
                    for (int d2 = 1; d2 <= N - y; d2++) {
                        if (x + d1 + d2 > N) continue;
                        calc(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static void calc(int x, int y, int d1, int d2) {
        int[] p = new int[5];

        // 1번
        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (r + c < x + y) {
                    p[0] += map[r][c];
                }
            }
        }

        // 2번
        for (int r = 1; r <= x + d2; r++) {
            for (int c = y + 1; c <= N; c++) {
                if (r - c < x - y) {
                    p[1] += map[r][c];
                }
            }
        }

        // 3번
        for (int r = x + d1; r <= N; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (r - c > (x + d1) - (y - d1)) {
                    p[2] += map[r][c];
                }
            }
        }

        // 4번
        for (int r = x + d2 + 1; r <= N; r++) {
            for (int c = y - d1 + d2; c <= N; c++) {
                if (r + c > (x + d2) + (y + d2)) {
                    p[3] += map[r][c];
                }
            }
        }

        // 5번은 나머지
        p[4] = total - (p[0] + p[1] + p[2] + p[3]);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int v : p) {
            min = Math.min(min, v);
            max = Math.max(max, v);
        }

        ans = Math.min(ans, max - min);
    }
}