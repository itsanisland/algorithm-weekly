import java.util.*;
import java.io.*;

class Main {

    public static List<Integer>[] graph;
    public static int[] indegrees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        indegrees = new int[n + 1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
        
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b); // a -> b
            indegrees[b]++;
        }

        List<Integer> result = topologicalSort(n);

        for (int i = 0; i < n; i++) {
            System.out.print(result.get(i) + " ");
        }
    }

    public static List<Integer> topologicalSort(int n) {
        List<Integer> result = new ArrayList<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();

            for (int next : graph[cur]) {
                indegrees[next]--;

                if (indegrees[next] == 0) {
                    pq.offer(next);
                }
            }

            result.add(cur);
        }

        return result;
    }
}