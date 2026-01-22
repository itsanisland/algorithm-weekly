import java.util.*;
import java.io.*;

class Main {
    private final static int[] DY = {0, -1, 0, 1};
    private final static int[] DX = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[101][101];

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> list = new ArrayList<>();
            list.add(d);

            // 방향 리스트 생성
            for (int i = 0; i < g; i++) {
                int size = list.size();
                for (int j = size - 1; j >= 0; j--) { 
                    list.add((list.get(j) + 1) % 4);
                }
            }

            // 방향 리스트 생성 후 한 번만 이동
            visited[y][x] = true;
            for (int dir : list) {
                x += DX[dir];
                y += DY[dir];
                visited[y][x] = true;
            }
        }

        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (visited[i][j] && visited[i + 1][j] 
                    && visited[i][j + 1] && visited[i + 1][j + 1]) ans++;
            }
        }
        
        System.out.println(ans);
    }
}