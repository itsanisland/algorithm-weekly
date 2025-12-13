import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {
	
	static StringBuilder answerDfs;
	static StringBuilder answerBfs;
	
	static List<Integer>[] graph;
	
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] split = in.readLine().split(" ");
		
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int V = Integer.parseInt(split[2]);
		
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			String[] UV = in.readLine().split(" ");
			int u = Integer.parseInt(UV[0]);
			int v = Integer.parseInt(UV[1]);
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}
		
		answerDfs = new StringBuilder();
		answerBfs = new StringBuilder();
		
		visited = new boolean[N + 1];
		
		dfs(V);
		
		visited = new boolean[N + 1];
		
		bfs(V);
		
		System.out.println(answerDfs.toString());
		System.out.println(answerBfs.toString());
	}
	
	private static void dfs(int v) {
		visited[v] = true;
		answerDfs.append(v + " ");
		
		for (int next : graph[v]) {
			if (!visited[next]) {
				dfs(next);
			}
		}
	}

	private static void bfs(int v) {
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.offer(v);
		visited[v] = true;
		
		answerBfs.append(v + " ");
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			
			for (int next : graph[current]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.offer(next);
					answerBfs.append(next + " ");
				}
			}
		}
	}

}