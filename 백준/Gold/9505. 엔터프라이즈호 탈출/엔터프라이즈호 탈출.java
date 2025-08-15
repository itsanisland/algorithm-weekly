import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Edge implements Comparable<Edge> {
		int y, x;
		int time;

		public Edge(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	private static final int INF = Integer.MAX_VALUE;
	private static int[] dirY = { -1, 1, 0, 0 };
	private static int[] dirX = { 0, 0, -1, 1 };
	
	private static int T, K, W, H;
	private static Map<Character, Integer> time;
	private static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			K = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			time = new HashMap<>();
			map = new char[H][W];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				time.put(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
			}
			
			int r = 0, c = 0;
			
			for (int i = 0; i < H; i++) {
				String row = br.readLine();
				
				for (int j = 0; j < W; j++) {
					char ch = row.charAt(j);
					map[i][j] = ch;
					
					if (ch == 'E') {
						r = i; c = j;
					}
				}
			}
			
			System.out.println(dijkstra(r, c));
		}
	}
	
	private static int dijkstra(int r, int c) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(r, c, 0));
		
		int[][] minTime = new int[H][W];
		for (int i = 0; i < H; i++) Arrays.fill(minTime[i], INF);
		minTime[r][c] = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			// 1) 오래된 상태 스킵
	        if (cur.time > minTime[cur.y][cur.x]) continue;

	        // 2) 경계 도달 시 조기 종료 : 가장 먼저 경계(탈출) 칸을 PQ에서 꺼냈을 때 그 비용이 최소
	        if (cur.y == 0 || cur.y == H - 1 || cur.x == 0 || cur.x == W - 1) {
	            return cur.time;
	        }

	        // 3) 인접 확장
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dirY[d];
				int nx = cur.x + dirX[d];
				
				if (!isValid(ny, nx)) continue;
				
				char ch = map[ny][nx];

	            // 지형 비용 없으면 통과 불가(또는 적절히 처리)
	            if (!time.containsKey(ch)) continue;

	            int nt = cur.time + time.get(ch);
				if (nt < minTime[ny][nx]) {
					minTime[ny][nx] = nt;
					pq.offer(new Edge(ny, nx, nt));
				}
			}
		}
		
		// 탈출 불가한 경우
	    return INF;
	}

	private static boolean isValid(int nextY, int nextX) {
		return nextY >= 0 && nextY < H && nextX >= 0 && nextX < W;
	}
}