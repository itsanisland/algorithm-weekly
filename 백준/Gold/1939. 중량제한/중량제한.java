import java.util.*;
import java.io.*;

class Main {

    public static List<Edge>[] graph;
    public static int ans = 0;

    public static class Edge {
        int to, c;

        Edge(int to, int c) {
            this.to = to;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        
        int left = 0, right = 0;
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
            right = Math.max(right, c);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        
        while (left <= right) {
            int mid = (left + right) / 2;
 
            if (bfs(start, dest, n, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(ans);
    }

    public static boolean bfs(int start, int dest, int n, int c) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == dest) {
                ans = Math.max(ans, c);
                return true;
            }
            
            for (Edge next : graph[cur]) {
                if (c <= next.c && !visited[next.to]) {
                    visited[next.to] = true;
                    q.offer(next.to);
                }
            }
        }

        return false;
    }
}