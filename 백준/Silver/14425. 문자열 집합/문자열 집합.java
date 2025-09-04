import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static Map<String, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			map.putIfAbsent(br.readLine(), 1);
		}
		
		int ans = 0;
		
		for (int i = 0; i < M; i++) {
			ans += map.getOrDefault(br.readLine(), 0);
		}
		
		System.out.println(ans);
	}

}