import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, q;
	private static int[] degrees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		degrees = new int[N + 1];
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st =  new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			degrees[a]++;
			degrees[b]++;
		}
		
		q = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < q; i++) {
			StringTokenizer st =  new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			String ans = "yes";
			
			if (t == 1 && degrees[k] < 2) {
				ans = "no";
			}
			
			sb.append(ans).append("\n");
		}

		System.out.println(sb);
	}

}