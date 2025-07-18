import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, d;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		perm(0, 0, new boolean[d]);

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static void perm(int n, int value, boolean[] visited) {
		if (n == d) {
			if (value > N) {
				result = Math.min(result, value);
			}
			return;
		}

		for (int i = 0; i < d; i++) {
			if (!visited[i]) {
				if (n == 0 && i == 0) continue; // 0이 맨 앞에 오는 경우 방지
				visited[i] = true;
				perm(n + 1, value * d + i, visited);
				visited[i] = false;
			}
		}
	}
}