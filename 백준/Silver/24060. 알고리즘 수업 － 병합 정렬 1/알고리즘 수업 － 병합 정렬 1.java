import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, K;
	static int[] a, tmp;
	private static int writeCnt = 0; // a에 값이 써질 때마다 +1
    private static int ans = -1;  // K번째에 써진 값(없으면 -1)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		a = new int[N];
		tmp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		mergeSort(0, N - 1);
		
		System.out.println(ans);
	}
	
	private static void mergeSort(int p, int r) {
		if (ans != -1) return;  
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(p, q);
			mergeSort(q + 1, r);
			merge(p, q, r);
		}
	}

	private static void merge(int p, int q, int r) {
		if (ans != -1) return;  
		
		int i = p, j = q + 1, t = 0;
		
		while (i <= q && j <= r) {
			if (a[i] <= a[j]) {
				tmp[t++] = a[i++];
			} else {
				tmp[t++] = a[j++];
			}
		}
		
		while (i <= q) {
			tmp[t++] = a[i++];
		}
		
		while (j <= r) {
			tmp[t++] = a[j++];
		}
		
		i = p; t = 0;
		
		while (i <= r) {
			a[i] = tmp[t];
			if (++writeCnt == K && ans == -1) {
				ans = a[i];
				return;
			}
			i++; t++;
		}
	}

}