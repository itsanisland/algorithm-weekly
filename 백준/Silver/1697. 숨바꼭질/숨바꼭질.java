import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(bfs(x, k));
    }

    public static int bfs(int x, int k) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { x, 0 });

        boolean[] visited = new boolean[200_002];
        visited[x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int sec = cur[1];

            if (curX == k) return sec;

            if (curX + 1 < 200_002 && !visited[curX + 1]) {
                q.offer(new int[] { curX + 1, sec + 1 });
                visited[curX + 1] = true;
            }

            if (curX - 1 >= 0 && !visited[curX - 1]) {
                q.offer(new int[] { curX - 1, sec + 1 });
                visited[curX - 1] = true;
            }

            if (curX * 2 < 200_002 && !visited[curX * 2]) {
                q.offer(new int[] { curX * 2, sec + 1 });
                visited[curX * 2] = true;
            }
        }

        return -1;
    }
}