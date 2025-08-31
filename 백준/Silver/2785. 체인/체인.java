import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int[] chains;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		chains = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			chains[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(chains);
		
		int used = 0;
		int rem = N - 1;
		for (int i = 0; i < N; i++) {
			if (used + chains[i] >= rem) {
				break;
			} else {
				used += chains[i];
				rem--;
			}
		}
		
		System.out.println(rem);
	}

}