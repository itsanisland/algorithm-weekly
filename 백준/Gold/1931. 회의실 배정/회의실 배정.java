import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static PriorityQueue<MeetingRoom> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			MeetingRoom meetingRoom = new MeetingRoom(start, end);
			
			pq.add(meetingRoom);			
		}
		
		int result = 1;
		int endTime = pq.poll().end;
		
		while (!pq.isEmpty()) {
			MeetingRoom meetingRoom = pq.poll();
			
			if (meetingRoom.start >= endTime) {
				endTime = meetingRoom.end;
				result++;
			}
		}
		
		System.out.println(result);
	}
	
	public static class MeetingRoom implements Comparable<MeetingRoom> {
		public int start, end;

		public MeetingRoom(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(MeetingRoom o) {
		    if (this.end == o.end) {
		        return this.start - o.start;  // 시작 빠른 순
		    }
		    return this.end - o.end;  // 끝나는 빠른 순
		}
	}

}