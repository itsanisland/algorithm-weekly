import java.util.*;
import java.io.*;

class Main {

    public static List<Integer>[] graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        
        System.out.println(bfs(1, n));
    }

    public static int bfs(int v, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(v);

        boolean[] visited = new boolean[n + 1];
        visited[v] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}