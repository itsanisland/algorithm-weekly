import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	
	private static int N;
	private static int[] buildTime;
	private static int[] result;
	private static int[] inDegree;
	private static List<List<Integer>> graph;
	private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        buildTime = new int[N + 1];       // 각 건물을 짓는 데 걸리는 시간
        result = new int[N + 1];          // 각 건물의 최종 소요 시간
        inDegree = new int[N + 1];        // 진입 차수
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int pre = Integer.parseInt(st.nextToken());
                if (pre == -1) break;

                graph.get(pre).add(i);
                inDegree[i]++;
            }
        }

        queue = new ArrayDeque<>();

        // 진입 차수 0인 건물부터 큐에 삽입
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                result[i] = buildTime[i];
            }
        }

        // 위상 정렬
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : graph.get(now)) {
                inDegree[next]--;
                result[next] = Math.max(result[next], result[now] + buildTime[next]);
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(result[i]);
        }
    }
}