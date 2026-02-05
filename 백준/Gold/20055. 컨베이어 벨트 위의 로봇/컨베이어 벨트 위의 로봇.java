import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[2 * N];
        boolean[] robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int step = 0;
        int zeroCnt = 0;

        while (zeroCnt < K) {
            step++;

            // 1. 벨트 회전
            int last = A[2 * N - 1];
            System.arraycopy(A, 0, A, 1, 2 * N - 1);
            A[0] = last;

            System.arraycopy(robot, 0, robot, 1, N - 1);
            robot[0] = false;
            robot[N - 1] = false;

            // 2. 로봇 이동
            for (int i = N - 2; i >= 0; i--) {
                if (robot[i] && !robot[i + 1] && A[i + 1] > 0) {
                    robot[i] = false;
                    robot[i + 1] = true;
                    A[i + 1]--;
                    if (A[i + 1] == 0) zeroCnt++;
                }
            }
            robot[N - 1] = false;

            // 3. 로봇 올리기
            if (A[0] > 0 && !robot[0]) {
                robot[0] = true;
                A[0]--;
                if (A[0] == 0) zeroCnt++;
            }
        }

        System.out.println(step);
    }
}