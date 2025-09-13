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
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			
			switch (cmd) {
			case 1:
				dq.offerFirst(Integer.parseInt(st.nextToken()));
				break;
			case 2:
				dq.offerLast(Integer.parseInt(st.nextToken()));
				break;
			case 3:
				System.out.println(dq.isEmpty() ? -1 : dq.pollFirst());
				break;
			case 4:
				System.out.println(dq.isEmpty() ? -1 : dq.pollLast());
				break;
			case 5:
				System.out.println(dq.size());
				break;
			case 6:
				System.out.println(dq.isEmpty() ? 1 : 0);
				break;
			case 7:
				System.out.println(dq.isEmpty() ? -1 : dq.peekFirst());
				break;
			case 8:
				System.out.println(dq.isEmpty() ? -1 : dq.peekLast());
				break;
			}
		}
	}

}