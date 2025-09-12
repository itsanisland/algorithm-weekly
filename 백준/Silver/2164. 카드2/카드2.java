import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Deque<Integer> q = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}

		while (q.size() >= 2) {
			q.pop();
			q.offer(q.pop());
		}

		System.out.println(q.pop());
	}

}