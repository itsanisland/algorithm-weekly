import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private final static int[] DY = { -1, 0, 1, 0 };
	private final static int[] DX = { 0, 1, 0, -1 };
	
	private static int N, M;
	private static int[][] map;
	private static boolean[][] cleaned;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		cleaned = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(r, c, d));
	}
	
	private static int bfs(int r, int c, int d) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c, d });
		
		int cnt = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0], x = cur[1];
			d = cur[2];
			
			if (!cleaned[y][x]) {
				cleaned[y][x] = true;
				cnt++;
			}
			
			boolean flag = true;
			
			int nd = d;
			for (int i = 1; i <= 4; i++) {
				// 반시계 방향으로 90도 회전
				nd = nd - 1 < 0 ? 3 : nd - 1;
				int ny = y + DY[nd], nx = x + DX[nd];
				
				if (isValid(ny, nx) && map[ny][nx] == 0 && !cleaned[ny][nx]) {
					// 전진 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
					q.offer(new int[] { ny, nx, nd });
					
					flag = false;
					break;
				}
			}
			
			if (flag) { // 청소되지 않은 빈칸이 없는 경우
				nd = (d + 2) % 4; // 후진
				y += DY[nd];
				x += DX[nd];
				
				if (isValid(y, x)) {
					if (map[y][x] == 0) { // 후진할 수 있으면 한 칸 후진
						q.offer(new int[] { y, x, d });
					} else { // 후진할 수 없으면(벽) 작동 멈추기
						break;
					}
				} else {
					break;
				}
			} 
		}
		
		return cnt;
	}
	
	private static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
}