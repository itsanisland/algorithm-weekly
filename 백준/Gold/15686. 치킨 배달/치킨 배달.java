import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map;
	static int[] selected;
	static List<int[]> chickens;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		chickens = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					chickens.add(new int[] { i, j });
				}
			}
		}

		for (int i = 1; i <= M; i++) {
			selected = new int[i];
			comb(0, i, 0);
		}

		out.append(answer);
		
		System.out.println(out);
	}
	
	static int getDist() {
		int dist = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					int minDist = Integer.MAX_VALUE;
					for (int k : selected) {
						minDist = Math.min(minDist, Math.abs(i - chickens.get(k)[0]) + Math.abs(j - chickens.get(k)[1]));
					}
					dist += minDist;
				}
			}
		}
		return dist;
	}
	
	static void comb(int r, int cnt, int idx) {
		if (r == cnt) {
			answer = Math.min(answer, getDist());
			return;
		}
		
		for (int i = idx; i < chickens.size(); i++) {
			selected[r] = i;
			comb(r + 1, cnt, i + 1);
		}
	}

}