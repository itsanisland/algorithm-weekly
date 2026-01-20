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

        for (int i = start; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                // 현재 위치, 왼쪽, 오른쪽에 이미 사다리가 있으면 설치 불가
                if (ladder[i][j] || ladder[i][j - 1] || ladder[i][j + 1]) continue;

                ladder[i][j] = true;      // 사다리 심기
                dfs(cnt + 1, i);         // 재귀 호출 (현재 행 i부터 탐색하도록 최적화)
                ladder[i][j] = false;      // 사다리 뽑기 (복구)
            }
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

        dfs(0, 1); // 1행부터 시작

        System.out.println(answer == 4 ? -1 : answer);
    }
}