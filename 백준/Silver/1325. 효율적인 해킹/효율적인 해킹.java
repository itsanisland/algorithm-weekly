import java.util.*;
import java.io.*;

class Main {

    public static List<Integer>[] graph;
    public static int[] memo;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b); // 도착 노드 -> 출발 노드
        }

        memo = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            bfs(i, n);
        }

        int max = 0;

        for (int i = 1; i <= n; i++) {
            max = Math.max(max, memo[i]);
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 1; i <= n; i++) {
            if (memo[i] == max) {
                sb.append(i + " ");
            }
        }

        System.out.println(sb);
    }

    public static void bfs(int v, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(v);

        boolean[] visited = new boolean[n + 1];
        visited[v] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    memo[next]++; // 현재 노드(cur)에 도달할 수 있는 노드들의 값 증가
                }
            }
        }
    }
}