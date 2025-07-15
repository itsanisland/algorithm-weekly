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
		
		st = new StringTokenizer(br.readLine());
		
		int[] array = new int[n];
		
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(st.nextToken());
			array[i] = input;
		}
		
		st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		
		Arrays.sort(array);
		
		int left = 1;
		int right = array[n - 1];
		int result = 0;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			long total = 0;
			
			for (int i = 0; i < n; i++) {
				total += Math.min(array[i], mid);
			}
			
			if (total <= m) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(result);
	}

}