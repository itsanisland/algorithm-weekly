import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int y, x;

		public Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[] selected;
	static List<Pair> empties;
	static List<Pair> virus;
	static boolean[][] visited;
	static int result = Integer.MIN_VALUE;
	
	static final int[] DY = { -1, 1, 0, 0 };
	static final int[] DX = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		empties = new ArrayList<>();
		virus = new ArrayList<>();
		selected = new int[3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 0) {
					empties.add(new Pair(i, j));
				}
				
				if (map[i][j] == 2) {
					virus.add(new Pair(i, j));
				}
			}
		}
		
		comb(0, 0);
		
		System.out.println(result);
	}
	
	static void comb(int r, int idx) {
		if (r == 3) {
			for (int i = 0; i < 3; i++) {
				Pair pair = empties.get(selected[i]);
				map[pair.y][pair.x] = 1;
			}
			
			result = Math.max(result, bfs());
			
			
			for (int i = 0; i < 3; i++) {
				Pair pair = empties.get(selected[i]);
				map[pair.y][pair.x] = 0;
			}
			
			return;
		}
		
		for (int i = idx; i < empties.size(); i++) {
			selected[r] = i;
			comb(r + 1, i + 1);
		}
	}
	
	static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static int bfs() {
		Queue<Pair> queue = new ArrayDeque<>(virus);
		visited = new boolean[N][M];
		
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++){
			temp[i] = Arrays.copyOf(map[i], M);
        }
		
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = current.y + DY[i];
				int nx = current.x + DX[i];
				
				if (!isValid(ny, nx) || visited[ny][nx]) { continue; }
				
				if (temp[ny][nx] == 0) {
					visited[ny][nx] = true;
					temp[ny][nx] = 2;
					queue.offer(new Pair(ny, nx));
				}
			}
		}
		
		int safeAreaCnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] == 0) {
					safeAreaCnt++;
				}
			}
		}
		
		return safeAreaCnt;
	}

}