import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] A = new int[N * 2];
        boolean[] robots = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        boolean finished = false;
        int step = 0;

        while (!finished) {
            step++;
            
            // 컨테이너 벨트 회전
            int lastA = A[N * 2 - 1];
            System.arraycopy(A, 0, A, 1, N * 2 - 1);
            A[0] = lastA;

            // 로봇 회전
            System.arraycopy(robots, 0, robots, 1, N - 1);
            robots[0] = false;
            robots[N - 1] = false;

            for (int i = N - 2; i >= 0; i--) {
                if (robots[i] && !robots[i + 1] && A[i + 1] > 0) {
                    robots[i] = false;
                    robots[i + 1] = true;
                    A[i + 1]--;
                }
            }

            // 로봇 올리기
            if (A[0] > 0) {
                robots[0] = true;
                A[0]--;
            }

            int zeroCnt = 0;
            for (int i = 0; i < N * 2; i++) {
                if (A[i] == 0) zeroCnt++;
            }

            if (zeroCnt >= K) finished = true;
        }
        
        System.out.println(step);
    }
}