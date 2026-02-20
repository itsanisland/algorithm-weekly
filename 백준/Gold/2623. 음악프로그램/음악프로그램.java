import java.util.*;
import java.io.*;

class Main {

    static int N;
    static List<Integer>[] graph;
    static int[] in;
    
    static List<Integer> sort() { // 위상 정렬
        List<Integer> ret = new ArrayList<>();

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                ret.add(i);
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph[cur]) {
                in[next]--;
                if (in[next] == 0) {
                    ret.add(next);
                    q.offer(next);
                }
            }
        }

        return ret;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        
        in = new int[N + 1];

        while (M-- > 0) {
            String[] s = br.readLine().split(" ");
            int len = Integer.parseInt(s[0]);
            for (int i = 1; i < len; i++) {
                int from = Integer.parseInt(s[i]);
                int to = Integer.parseInt(s[i + 1]);
                graph[from].add(to);
                in[to]++;
            }
        }

        List<Integer> rslt = sort();
        if (rslt.size() < N) {
            System.out.println(0);
        } else {
            for (int i = 0; i < N; i++) {
                System.out.println(rslt.get(i));
            }
        }
    }
}