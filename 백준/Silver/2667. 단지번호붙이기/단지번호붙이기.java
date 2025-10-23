import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

	private final static int[] DY = { -1, 1, 0, 0 };
	private final static int[] DX = { 0, 0, -1, 1};
	
	private static int N;
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for  (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt("" + s.charAt(j));
			}
		}
		
		List<Integer> counts = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					counts.add(bfs(i, j, map[i][j]));
				}
			}
		}
		
		System.out.println(counts.size());
		counts.stream()
		.sorted()
		.forEach(System.out::println);
	}
	
	private static int bfs(int sy, int sx, int n) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sy, sx });
		
		visited[sy][sx] = true;
		
		int cnt = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0], x = cur[1];

			for (int i = 0; i < 4; i++) {
				int ny = y + DY[i], nx = x + DX[i];
				
				if (isValid(ny, nx) && map[ny][nx] == 1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					q.offer(new int[] { ny, nx });
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
}