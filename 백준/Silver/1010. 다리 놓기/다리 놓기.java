import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		dp = new int[30][30];

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			sb.append(comb(M, N)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int comb(int n, int r) {
		if (r == 0 || n == r) return 1; // 하나도 안 고르거나 다 고르는 경우의 수는 1
		
		if (dp[n][r] != 0) return dp[n][r];
		
		return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
	}

}