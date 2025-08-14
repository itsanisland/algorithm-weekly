import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static final int INF = Integer.MAX_VALUE;
	
	private static int N, M;
	private static int[] nums;
	private static int[] seg;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		seg = new int[4 * N];
		
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		build(1, 1, N);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			System.out.println(query(1, 1, N, a, b));
		}
	}
	
	private static void build(int idx, int s, int e) {
		if (s == e) {
			seg[idx] = nums[s];
			return;
		}
		
		int mid = (s + e) / 2;
		
		build(idx * 2, s, mid);
		build(idx * 2 + 1, mid + 1, e);
		
		seg[idx] = Math.min(seg[idx * 2], seg[idx * 2 + 1]);
	}
	
	private static int query(int idx, int s, int e, int l, int r) {
		if (r < s || e < l) return INF;
		if (l <= s && e <= r) return seg[idx];
		
		int mid = (s + e) / 2;
		
		return Math.min(query(idx * 2, s, mid, l, r), query(idx * 2 + 1, mid + 1, e, l, r));
	}
}