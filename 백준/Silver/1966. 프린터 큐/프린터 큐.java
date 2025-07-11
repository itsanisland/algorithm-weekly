import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
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
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

			
			for (int i = 0; i < N; i++) {
				int priority = Integer.parseInt(st.nextToken());
			    queue.offer(new int[] {i, priority});
			    pq.offer(priority);
			}

			int result = 0;
			
			while (!queue.isEmpty()) {
				int[] current = queue.poll();
				int idx = current[0];
				int priority = current[1];
				
				if (priority == pq.peek()) {
					result++;
					pq.poll();
					if (idx == M) {
						break;
					}
				} else {
					queue.offer(current);
				}
			}
			
			System.out.println(result);
		}
	}

}