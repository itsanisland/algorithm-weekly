import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { 1, 0, -1, 0 };
    public final static int[] DX = { 0, -1, 0, 1 };

    public static int n, m, k, ans = 5_000;
    public static int[][] a, rot;
    public static int[] selected;
    public static boolean[] visited;

    public static void rotate(int idx, int[][] arr) {
        int r = rot[idx][0] - 1;
        int c = rot[idx][1] - 1;
        int s = rot[idx][2];
    
        for (int layer = 1; layer <= s; layer++) {
            int top = r - layer;
            int left = c - layer;
            int bottom = r + layer;
            int right = c + layer;
    
            int temp = arr[top][left];
    
            // left column ↑
            for (int i = top; i < bottom; i++)
                arr[i][left] = arr[i + 1][left];
    
            // bottom row ←
            for (int i = left; i < right; i++)
                arr[bottom][i] = arr[bottom][i + 1];
    
            // right column ↓
            for (int i = bottom; i > top; i--)
                arr[i][right] = arr[i - 1][right];
    
            // top row →
            for (int i = right; i > left + 1; i--)
                arr[top][i] = arr[top][i - 1];
    
            arr[top][left + 1] = temp;
        }
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
                rotate(idx, cp);
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