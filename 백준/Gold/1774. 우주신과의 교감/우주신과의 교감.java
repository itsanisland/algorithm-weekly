import java.util.*;
import java.io.*;

class Main {

    public static class Edge implements Comparable<Edge> {
        int from, to;
        double w;
        
        Edge(int from, int to, double w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }

    public static List<Edge> edges;
    public static int[] parents;
    public static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    public static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA < parentB) {
            parents[parentB] = parentA;
        } else if (parentB < parentA) {
            parents[parentA] = parentB;
        }
    }
    
    public static double getDist(double[] a, double[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    public static double kruskal() {
        double dist = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.from) != find(cur.to)) {
                union(cur.from, cur.to);
                dist += cur.w;
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        double[][] coords = new double[n + 1][2];
        edges = new ArrayList<>();
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) parents[i] = i;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            coords[i][0] = Double.parseDouble(st.nextToken());
            coords[i][1] = Double.parseDouble(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                pq.offer(new Edge(i, j, getDist(coords[i], coords[j])));
            }
        }

        System.out.printf("%.2f\n", kruskal());
    }
}