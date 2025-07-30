import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int T, N, M;
	private static int[] A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			B = new int[M];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(A);
			Arrays.sort(B);
			
			int result = 0;
			
			for (int i = N - 1; i >= 0; i--) {
				int cnt = lowerBound(0, M, A[i]);
				
				result += cnt;
			}
			
			System.out.println(result);
		}
	}
	
	private static int lowerBound(int start, int end, int k) {
		while (start < end) {
			int mid = (start + end) / 2;
			
			if (B[mid] < k) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		return start;
	}

}