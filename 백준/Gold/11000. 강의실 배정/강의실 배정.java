import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static PriorityQueue<Integer> pq; // 각 강의실의 종료 시간(min heap)
	private static int[][] lectures;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		lectures = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			lectures[i][0] = start;
			lectures[i][1] = end;
		}
		
		Arrays.sort(lectures, (a, b) -> Integer.compare(a[0], b[0])); // 시작 시간 기준 정렬

		for (int[] lecture : lectures) {
			// pq에 수업이 있고 마지막에 진행했던 수업의 종료 시간 <= 현재 수업의 시작 시간 
		    if (!pq.isEmpty() && pq.peek() <= lecture[0]) {
		        pq.poll(); // 재사용
		    }
		    
		    pq.offer(lecture[1]); // 새 강의실 or 갱신
		}
		
		System.out.println(pq.size());
	}

}