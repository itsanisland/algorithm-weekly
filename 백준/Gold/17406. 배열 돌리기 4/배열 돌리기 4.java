import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { 1, 0, -1, 0 };
    public final static int[] DX = { 0, -1, 0, 1 };

    public static int n, m, k, ans = 5_000;
    public static int[][] a, rot;
    public static int[] selected;
    public static boolean[] visited;

    public static int[][] rotate(int idx, int[][] arr) {
        int r = rot[idx][0] - 1, c = rot[idx][1] - 1, s = rot[idx][2];

        int[][] newArr = new int[n][m];
        for (int i = 0; i < n; i++) {
            newArr[i] = arr[i].clone();
        }
        
        for (int i = 1; i < s + 1; i++) { // 오른쪽-위 대각선
            int rr = r - i, cc = c + i; // 현재 위치
            for (int d = 0; d < 4; d++) {
                for (int j = 0; j < i * 2; j++) {
                    int rrr = rr + DY[d], ccc = cc + DX[d]; // 다음 위치
                    newArr[rrr][ccc] = arr[rr][cc];
                    rr = rrr; cc = ccc;
                }
            }
        }

        return newArr;
    }

    public static int getMin(int[][] arr) {
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += arr[i][j];
            }
            ret = Math.min(ret, sum);
        }
        return ret;
    } 

    public static void dfs(int cnt) {
        if (cnt == k) {
            int[][] cp = new int[n][m];
            for (int i = 0; i < n; i++) {
                cp[i] = a[i].clone();
            }
            for (int idx : selected) {
                cp = rotate(idx, cp);
            }
            ans = Math.min(ans, getMin(cp));
            return;
        }

        for (int i = 0; i < k; i++) {
            if (visited[i]) continue;
            selected[cnt] = i;
            visited[i] = true;
            dfs(cnt + 1);
            visited[i] = false;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        rot = new int[k][3];
        selected = new int[k];
        visited = new boolean[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rot[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        
        System.out.println(ans);
    }
}