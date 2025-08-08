import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, X;
	private static int[] a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		a = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int lo = 1, hi = N;
		int ans = N;
		
		while (lo <= hi) {
			int mid = (lo + hi) >> 1;
			
			if (check(mid)) {
				ans = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		
		System.out.println(ans);
	}
	
	private static boolean check(int k) {
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		for (int i = 0; i < k; i++) pq.add(0L);
		
		for (int t : a) {
			Long cur = pq.poll();
			
			cur += t;
			
			if (cur > X) return false;
			
			pq.add(cur);
		}
		
		return true;
	}

}