import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int K;
	private static int[] array;
	private static List<Integer>[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		array = new int[(int) Math.pow(2, K) - 1];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		result = new List[K];
		
		for (int i = 0; i < K; i++) {
			result[i] = new ArrayList<>();
		}
		
		dfs(0, array.length - 1, 0);
		
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < result[i].size(); j++) {
				System.out.print(result[i].get(j) + " ");
			}
			System.out.println();
		}
	}

	static void dfs(int start, int end, int depth) {
	    if (depth == K) return;

	    int mid = (start + end) / 2;
	    result[depth].add(array[mid]);

	    dfs(start, mid - 1, depth + 1);
	    dfs(mid + 1, end, depth + 1);
	}
}