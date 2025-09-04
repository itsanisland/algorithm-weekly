import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static Set<String> set1;
	private static Set<String> set2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		set1 = new HashSet<>();
		set2 = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			set1.add(br.readLine());
		}
		
		for (int i = 0; i < M; i++) {
			String name = br.readLine();
			if (set1.contains(name)) {
				set2.add(name);
			}
		}
		
		String[] arr = set2.toArray(new String[0]);
		Arrays.sort(arr);
		
		System.out.println(arr.length);
		
		for (int i = 0; i < arr.length; i++) System.out.println(arr[i]);
	}

}