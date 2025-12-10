import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		Queue<Integer> pq = new PriorityQueue<>();
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
			pq.offer(arr[i]);
		}
		
		int ret = 0;
		while (pq.size() > 1) {
			int temp1 = pq.poll();
			int temp2 = pq.poll();
			
			pq.offer(temp1 + temp2);
			ret += temp1 + temp2;
		}
		
		System.out.println(ret);
	}

}