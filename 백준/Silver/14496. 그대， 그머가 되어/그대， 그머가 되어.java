import java.util.*;
import java.io.*;

class Main {

    private static final int INF = Integer.MAX_VALUE;
    
    private static List<Integer>[] graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        int answer = bfs(a, b, n);

        System.out.println(answer);
    }

    private static int bfs(int a, int b, int n) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { a, 0 });

        boolean[] visited = new boolean[n + 1];
        visited[a] = true;

        while (!q.isEmpty()) {
            int[] from = q.poll();

            if (from[0] == b) return from[1];

            for (int to : graph[from[0]]) {
                if (visited[to]) continue;
                visited[to] = true;
                q.offer(new int[] { to, from[1] + 1 });
            }
        }

        return -1;
    }
}