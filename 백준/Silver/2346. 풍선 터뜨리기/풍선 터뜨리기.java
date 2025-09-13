import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Deque<int[]> dq = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			dq.offer(new int[] { i, Integer.parseInt(st.nextToken()) });
		}
		
		StringBuilder sb = new StringBuilder();
		
		while (!dq.isEmpty()) {
			int[] cur = dq.pollFirst();
			
			int num = cur[0];
			int seq = cur[1];
			
			sb.append(num).append(" ");

		    if (dq.isEmpty()) break; // 마지막 풍선이면 회전 불필요

		    if (seq > 0) {
		        int k = (seq - 1) % dq.size();      // 한 개 꺼냈으니 -1
		        for (int i = 0; i < k; i++) dq.offerLast(dq.pollFirst());
		    } else {
		        int k = (-seq) % dq.size();         // 음수는 |seq|만큼 오른쪽 회전
		        for (int i = 0; i < k; i++) dq.offerFirst(dq.pollLast());
		
		    }
		}
		
		sb.setLength(sb.length()-1);
		
		System.out.println(sb);
	}

}