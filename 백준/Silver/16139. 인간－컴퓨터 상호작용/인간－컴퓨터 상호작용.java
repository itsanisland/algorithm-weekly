import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static String S;
	private static int q;
	private static int[][] prefixSumCounts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		S = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		
		q = Integer.parseInt(st.nextToken());
		prefixSumCounts = new int[26][S.length() + 1];
		
		for (int i = 1; i <= S.length(); i++) {
			char ch = S.charAt(i - 1);
			int idx = ch - 'a';
			
            // 누적합 계산
			for (int j = 0; j < 26; j++) {
				prefixSumCounts[j][i] = prefixSumCounts[j][i - 1] + ((j == idx) ? 1 : 0);
			}
		}
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			
			char ch = st.nextToken().charAt(0);
			int l = Integer.parseInt(st.nextToken()) + 1;
			int r = Integer.parseInt(st.nextToken()) + 1;
			
			System.out.println(prefixSumCounts[ch - 'a'][r] - prefixSumCounts[ch - 'a'][l - 1]);
		}
	}

}