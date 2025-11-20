import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int N;
	private static int[] counts;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		counts = new int[10_001];
		
		for (int i = 0; i < N; i++) {
			counts[Integer.parseInt(br.readLine())]++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= 10_000; i++) {
			for (int j = 0; j < counts[i]; j++) {
				sb.append(i + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}