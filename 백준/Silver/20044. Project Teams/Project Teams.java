import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		st =  new StringTokenizer(br.readLine());
		
		int[] arr = new int[2 * n];

		for (int i = 0; i < 2 * n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		
		int result = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			result = Math.min(result, arr[i] + arr[2 * n - 1 - i]);
		}
		
		System.out.println(result);
	}

}