import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int F, S, G, U, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int result = bfs();
		
		System.out.println(result == -1 ? "use the stairs" : result);
	}

	private static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[F + 1];
		
		queue.offer(new int[] {S, 0});
		visited[S] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int pos = current[0];
			int cnt = current[1];
			
			if (pos == G) {
				return cnt;
			}
			
			if (pos + U <= F && !visited[pos + U]) {
				queue.offer(new int[] {pos + U, cnt + 1});
				visited[pos + U] = true; 
			}
			
			if (pos - D >= 1 && !visited[pos - D]) {
				queue.offer(new int[] {pos - D, cnt + 1});
				visited[pos - D] = true; 
			}
		}
		
		return -1;
	}
	
}