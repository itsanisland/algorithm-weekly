import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static int[] T, P;
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		T = new int[N + 1];
		P = new int[N + 1];
		dp = new int[N + 2]; // i일에 도달했을 때 가질 수 있는 최대 이익
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			// i일에 상담을 한다 -> 종료일에 수익 반영
			if (i + T[i] <= N + 1) {
				dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
			}
			
			// i일에 상담을 안 한다 -> 다음 날로 이익을 넘김
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
		}
		
		System.out.println(dp[N + 1]);
	}

}