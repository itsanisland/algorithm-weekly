import java.util.*;
import java.io.*;

class Main {

    public static char[][] T;

    public static void turn(int num, int dir) {
        char tmp;
        if (dir == 1) { // 시계 방향 ->
            tmp = T[num][7];
            for (int i = 7; i > 0; i--) {
                T[num][i] = T[num][i - 1];
            }
            T[num][0] = tmp;
        } else { // 반시계 방향 <-
            tmp = T[num][0];
            for (int i = 1; i < 8; i++) {
                T[num][i - 1] = T[num][i];
            }
            T[num][7] = tmp;
        }
    }

    public static void turnAll(int num, int dir) {
        // 회전 방향 배열
        int[] turnDir = new int[4];
        turnDir[num] = dir;

        // <- num: 왼쪽 전파
        for (int i = num; i > 0; i--) {
            // T[i - 1], T[i]
            if (T[i - 1][2] == T[i][6]) break;
            turnDir[i - 1] = -turnDir[i];
        }

        // num ->: 오른쪽 전파
        for (int i = num; i < 3; i++) {
            // T[i], T[i + 1]
            if (T[i][2] == T[i + 1][6]) break;
            turnDir[i + 1] = -turnDir[i];
        }

        // 실제 회전
        for (int i = 0; i < 4; i++) {
            if (turnDir[i] != 0) turn(i, turnDir[i]);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = new char[4][8];
        for (int i = 0; i < 4; i++) {
            T[i] = br.readLine().toCharArray();
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            turnAll(num, dir);
        }

        int score = 0;
        for (int i = 0; i < 4; i++) {
            score += T[i][0] == '0' ? 0 : 1 << i;
        }
        
        System.out.println(score);
    }
}