import java.io.*;
import java.util.*;

class Main {

    static int N, M, H;
    static boolean[][] ladder; // [행][열]
    static int ans = 4; // 최대 3개까지만 허용

    static boolean play() {
        for (int start = 1; start <= N; start++) {
            int pos = start;
            for (int i = 1; i <= H; i++) {
                if (ladder[i][pos]) pos++;
                else if (pos > 1 && ladder[i][pos - 1]) pos--;
            }
            if (pos != start) return false;
        }
        return true;
    }

    static void dfs(int depth, int idx) {
        if (depth >= ans) return;

        if (play()) {
            ans = depth;
            return;
        }

        if (depth == 3) return;

        for (int i = idx; i <= H * (N - 1); i++) {
            int r = (i - 1) / (N - 1) + 1;
            int c = (i - 1) % (N - 1) + 1;

            if (ladder[r][c]) continue;
            if (c > 1 && ladder[r][c - 1]) continue;
            if (c < N - 1 && ladder[r][c + 1]) continue;

            ladder[r][c] = true;
            dfs(depth + 1, i + 1);
            ladder[r][c] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        dfs(0, 1);

        System.out.println(ans > 3 ? -1 : ans);
    }
}