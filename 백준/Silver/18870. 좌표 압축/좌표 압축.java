import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] copy = Arrays.copyOf(nums, N);

		Arrays.sort(copy);
		
		Map<Integer, Integer> map = new HashMap<>();
		
		int rank = 0;
		for (int i = 0; i < N; i++) {
			if (map.get(copy[i]) != null) {
				continue;
			}
			
			map.putIfAbsent(copy[i], rank++);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(map.get(nums[i]) + " ");
		}
		
		System.out.println(sb.toString());
	}

}