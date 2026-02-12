import java.util.*;
import java.io.*;

class Main {

    static int[][] ns = new int[4][8];

    static void rotate(int idx, int d) {
        // 동시에 회전 -> 회전 여부만 저장하고 한번에 회전
        int[] rotated = new int[4];
        rotated[idx] = d;
        
        // 앞 톱니바퀴들 확인
        for (int prev = idx - 1; prev >= 0; prev--) {
            if (ns[prev][2] == ns[prev + 1][6]) break; // 극이 같으면, 회전 X
            else {
                rotated[prev] = -rotated[prev + 1]; // 이전 톱니바퀴와 반대 방향으로 회전
            }
        }

        // 뒤 톱니바퀴들 확인
        for (int next = idx + 1; next < 4; next++) {
            if (ns[next][6] == ns[next - 1][2]) break; // 극이 같으면, 회전 X
            else {
                rotated[next] = -rotated[next - 1]; // 이전 톱니바퀴와 반대 방향으로 회전
            }
        }

        // 회전
        for (int i = 0; i < 4; i++) {
            if (rotated[i] == 1) { // 시계 방향
                int tmp = ns[i][7];
                System.arraycopy(ns[i], 0, ns[i], 1, 7);
                ns[i][0] = tmp;
            } else if (rotated[i] == -1) {
                int tmp = ns[i][0];
                System.arraycopy(ns[i], 1, ns[i], 0, 7);
                ns[i][7] = tmp;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                ns[i][j] = s.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            rotate(idx, d);
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            ans += ns[i][0] * (1 << i);
        }
        
        System.out.println(ans);
    }
}