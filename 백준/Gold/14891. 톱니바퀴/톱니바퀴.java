import java.io.*;
import java.util.*;

class Main {

    static int[] gears = new int[4];

    static void rotate(int idx, int dir) {
        if (dir == 1) { // 시계
            int last = gears[idx] & 1;
            gears[idx] = (gears[idx] >> 1) | (last << 7);
        } else {        // 반시계
            int first = (gears[idx] >> 7) & 1;
            gears[idx] = ((gears[idx] << 1) & 0xFF) | first;
        }
    }

    static int getBit(int gear, int idx) {
        return (gear >> idx) & 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            int val = 0;
            for (int j = 0; j < 8; j++) {
                val = (val << 1) | (s.charAt(j) - '0');
            }
            gears[i] = val;
        }

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            int[] turnDir = new int[4];
            turnDir[num] = dir;

            // 왼쪽 전파
            for (int i = num; i > 0; i--) {
                if (getBit(gears[i], 1) == getBit(gears[i - 1], 5)) break;
                turnDir[i - 1] = -turnDir[i];
            }

            // 오른쪽 전파
            for (int i = num; i < 3; i++) {
                if (getBit(gears[i], 5) == getBit(gears[i + 1], 1)) break;
                turnDir[i + 1] = -turnDir[i];
            }

            // 실제 회전
            for (int i = 0; i < 4; i++) {
                if (turnDir[i] != 0) rotate(i, turnDir[i]);
            }
        }

        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (((gears[i] >> 7) & 1) == 1) {
                score += (1 << i);
            }
        }

        System.out.println(score);
    }
}