import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			Queue<int[]> queue = new ArrayDeque<>();
			
			for (int i = 0; i < N; i++) {
				queue.offer(new int[] {i, Integer.parseInt(st.nextToken())});
			}

			int result = 0;
			
			while (!queue.isEmpty()) {
				int[] current = queue.poll();
				int pos = current[0];
				int weight = current[1];
				
				boolean flag = false;
				
				for (int[] next : queue) {
					if (weight < next[1]) {
						queue.offer(current);
						flag = true;
						break;
					}
				}
				
				if (!flag) {
					result++;
					if (pos == M) {
						break;
					}
				}
			}
			
			System.out.println(result);
		}
	}

}