import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static int[] primeArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		primeArr = new int[M + 1];
		
		Arrays.fill(primeArr, 1);
		primeArr[1] = 0;
		
		for (int i = 2; i <= M; i++) {
			for (int j = i * 2; j <= M; j += i) {
				primeArr[j] = 0;
			}
		}
		
		for (int i = N; i <= M; i++) {
			if (primeArr[i] == 1) {
				System.out.println(i);
			}
		}
	}

}