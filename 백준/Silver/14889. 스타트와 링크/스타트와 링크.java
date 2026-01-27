import java.util.*;
import java.io.*;

class Main {

    static int N, S, ans = 38000;
    static int[][] power;

    static int getTotalPower(int[] selected) {
        int sum = 0;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                if (i == j) continue;
                sum += power[selected[i]][selected[j]];
            }
        }
        return sum;
    }

    static void comb(int s, int cnt, boolean[] selected) {
        if (cnt == N / 2) {
            int[] start = new int[N / 2];
            int[] link = new int[N / 2];

            int idx1 = 0, idx2 = 0;
            for (int i = 0; i < N; i++) {
                if (selected[i]) start[idx1++] = i;
                else link[idx2++] = i;
            }
            
            int sum1 = getTotalPower(start);
            int sum2 = getTotalPower(link);
            
            ans = Math.min(ans, Math.abs(sum1 - sum2));
            return;
        }

        for (int i = s; i < N; i++) {
            selected[i] = true;
            comb(i + 1, cnt + 1, selected);
            selected[i] = false;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        power = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                power[i][j] = Integer.parseInt(st.nextToken());
                S += power[i][j];
            }
        }
        
        comb(0, 0, new boolean[N]);
        
        System.out.println(ans);
    }
}