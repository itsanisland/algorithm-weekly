import java.util.*;
import java.io.*;

class Main {

    public static int n, ans;

    public static int[][] zip(int[][] map) {
        int[][] ret = new int[n][n];
        for (int y = 0; y < n; y++) {
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (map[y][i] > 0) {
                    list.add(map[y][i]);
                }
            }
            
            for (int i = 0; i < list.size() - 1; ) {
                if (list.get(i).equals(list.get(i + 1))) {
                    list.set(i, list.get(i) * 2);
                    list.remove(i + 1);
                    list.add(0);
                    i++; // 여기서 한 칸 스킵
                } else {
                    i++;
                }
            }

            while (list.remove(Integer.valueOf(0))) {};
            
            for (int i = 0; i < list.size(); i++) {
                ret[y][i] = list.get(i);
            }
        }
        return ret;
    }

    public static int[][] rotate90(int[][] map) {
        int[][] ret = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                ret[x][n - y - 1] = map[y][x]; 
            }
        }
        return ret;
    }

    public static void dfs(int d, int[][] map) {
        if (d == 5) {
            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            ans = Math.max(ans, max);
            return;
        }

        int[][] cur = map;

        for (int i = 0; i < 4; i++) {
            int[][] next = zip(cur);   // 같은 상태에서 한 방향 시도
            dfs(d + 1, next);
            cur = rotate90(cur);       // 방향만 바꿈
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, map);
        
        System.out.println(ans);
    }
}