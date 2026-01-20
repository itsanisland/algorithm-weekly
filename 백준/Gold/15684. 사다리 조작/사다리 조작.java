import java.util.*;
import java.io.*;

class Main {

    public static int N, M, H, answer = 4;
    public static boolean[][] ladder;

    static boolean play() {
        for (int start = 1; start <= N; start++) {
            int x = start;
            for (int y = 1; y <= H; y++) {
                if (ladder[y][x]) x++;
                else if (x > 1 && ladder[y][x - 1]) x--;
            }
            if (x != start) return false;
        }
        return true;
    }

    static void dfs(int cnt, int start) {
        if (cnt > 3) return;
        if (cnt >= answer) return;
    
        if (play()) {
            answer = cnt;
            return;
        }
    
        for (int idx = start; idx < H * (N - 1); idx++) {
            int i = idx / (N - 1) + 1;
            int j = idx % (N - 1) + 1;
    
            if (ladder[i][j]) continue;
            if (j > 1 && ladder[i][j - 1]) continue;
            if (j < N - 1 && ladder[i][j + 1]) continue;
    
            ladder[i][j] = true;
            dfs(cnt + 1, idx + 1);
            ladder[i][j] = false;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[H + 1][N + 1];
        
        if (M > 0) {
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                ladder[a][b] = true;
            }
        }

        dfs(0, 0);

        System.out.println(answer == 4 ? -1 : answer);
    }
}