import java.io.*;
import java.util.*;

public class Main {

    static int[] dice = new int[10];
    static int[] pos = new int[4];   // 말 위치 (노드 번호)
    static int ans = 0;

    // 그래프
    static int[] next = new int[33];
    static int[] blue = new int[33];
    static int[] score = new int[33];

    static void init() {
        // 점수 초기화 (메인 경로)
        for (int i = 0; i <= 20; i++) {
            score[i] = i * 2;
        }

        // 점수 (파란길 + 합류)
        score[21] = 13;
        score[22] = 16;
        score[23] = 19;

        score[24] = 22;
        score[25] = 24;

        score[26] = 28;
        score[27] = 27;
        score[28] = 26;

        score[29] = 25;
        score[30] = 30;
        score[31] = 35;
        score[32] = 0; // 도착

        // next (빨간 화살표)
        for (int i = 0; i < 20; i++) {
            next[i] = i + 1;
        }
        next[20] = 32; // 40 → 도착

        next[21] = 22;
        next[22] = 23;
        next[23] = 29;

        next[24] = 25;
        next[25] = 29;

        next[26] = 27;
        next[27] = 28;
        next[28] = 29;

        next[29] = 30;
        next[30] = 31;
        next[31] = 20; // 35 → 40

        // blue (파란 화살표)
        Arrays.fill(blue, -1);
        blue[5]  = 21; // 10 → 13
        blue[10] = 24; // 20 → 22
        blue[15] = 26; // 30 → 28
    }

    static int move(int cur, int d) {
        if (cur == 32) return 32;

        if (blue[cur] != -1) {
            cur = blue[cur];
            d--;
        }

        while (d-- > 0 && cur != 32) {
            cur = next[cur];
        }

        return cur;
    }

    static void dfs(int turn, int sum) {
        if (turn == 10) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int cur = pos[i];
            if (cur == 32) continue;

            int nextPos = move(cur, dice[turn]);

            // 충돌 체크
            boolean crash = false;
            for (int j = 0; j < 4; j++) {
                if (i != j && pos[j] == nextPos && nextPos != 32) {
                    crash = true;
                    break;
                }
            }
            if (crash) continue;

            pos[i] = nextPos;
            dfs(turn + 1, sum + score[nextPos]);
            pos[i] = cur;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        init();
        dfs(0, 0);

        System.out.println(ans);
    }
}