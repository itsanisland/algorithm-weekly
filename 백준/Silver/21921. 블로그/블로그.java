import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, X;
	private static int[] visitors;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		visitors = new int[N];
		
		for (int i = 0; i < N; i++) {
			visitors[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		int start = 0;
		
		for (int i = 0; i < X; i++) {
			sum += visitors[i];
		}
		
		int max = sum;
		int[] maxSum = new int[N];
		maxSum[X - 1] = sum;
		
		for (int i = X; i < N; i++) {
			sum -= visitors[start];
			sum += visitors[i];
			
			max = Math.max(max, sum);
			maxSum[i] = sum;
			
			start++;
		}
		
		System.out.println(max == 0 ? "SAD" : max);
		
		if (max != 0) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (maxSum[i] == max) {
					count++;
				}
			}
			System.out.println(count);
		}
	}

}