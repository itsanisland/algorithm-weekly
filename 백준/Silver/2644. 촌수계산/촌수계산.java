import java.util.*;
import java.io.*;

class Main {

    static int n;
    static List<Integer>[] graph;

    static int bfs(int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {a, 0});

        boolean[] visited = new boolean[n + 1];
        visited[a] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int from = cur[0];
            int d = cur[1];

            if (from == b) return d;

            for (int to : graph[from]) {
                if (visited[to]) continue;
                visited[to] = true;
                q.offer(new int[] {to, d + 1});
            }
        }

        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            graph[parent].add(child);
            graph[child].add(parent);
        }

        System.out.println(bfs(a, b));
    }
}