import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, k;
	private static int[] A, result, parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		
		A = new int[N + 1];
		result = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			result[i] = A[i];
		}

		makeSet();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			union(v, w);
		}

		int sum = 0;
		Set<Integer> set = new HashSet<>();
		
		for (int i = 1; i <= N; i++) {
			int root = find(i); // 반드시 최종 루트를 찾을 것
			if (!set.contains(root)) {
				sum += result[root];
				set.add(root);
			}
		}
		
		System.out.println(sum <= k ? sum : "Oh no");
	}

	private static void makeSet() {
		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (parents[a] == a) {
			return a;
		}

		return parents[a] = find(parents[a]); // path compression
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		// 이미 a 정점과 b 정점이 같은 집합이라면
		if (aRoot == bRoot) {
			return false;
		}
		
		// 항상 작은 루트를 대표로 두자 (가독성과 일관성을 위해)
		if (aRoot < bRoot) {
			parents[bRoot] = aRoot; // a가 새로운 집합의 대표자로 선정
			result[aRoot] = Math.min(result[aRoot], result[bRoot]);
		} else {
			parents[aRoot] = bRoot;
			result[bRoot] = Math.min(result[aRoot], result[bRoot]);
		}
		
		return true;
	}
}