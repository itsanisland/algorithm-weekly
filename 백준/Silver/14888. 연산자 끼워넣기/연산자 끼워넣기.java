import java.util.*;
import java.io.*;

class Main {

    static int N;
    static long min = 1_000_000_000, max = -1_000_000_000;
    static int[] num;
    static int[] op = new int[4];

    static void dfs(int cnt, long rslt) {
        if (cnt == N) {
            max = Math.max(max, rslt);
            min = Math.min(min, rslt);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--;
                if (i == 0) dfs(cnt + 1, rslt + num[cnt]);
                else if (i == 1) dfs(cnt + 1, rslt - num[cnt]);
                else if (i == 2) dfs(cnt + 1, rslt * num[cnt]);
                else dfs(cnt + 1, rslt / num[cnt]);
                op[i]++;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, num[0]);
        
        System.out.println(max);
        System.out.println(min);
    }
}