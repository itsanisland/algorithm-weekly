import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static List<Integer> boyUp, boyDown;
	private static List<Integer> girlUp, girlDown;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		boyUp = new ArrayList<>(); boyDown = new ArrayList<>();
		girlUp = new ArrayList<>(); girlDown = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if (height < 0) {
				boyDown.add(-height);
			} else {
				boyUp.add(height);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if (height < 0) {
				girlDown.add(-height);
			} else {
				girlUp.add(height);
			}
		}
		
		int ans = 0;
		int i = 0, j = 0;
		
		// CASE 1: boyUp-girlDown(boyHeight < girlHeight)
		Collections.sort(boyUp); Collections.sort(girlDown);
		while (i < boyUp.size() && j < girlDown.size()) {
			if (boyUp.get(i) < girlDown.get(j)) {
				ans++; i++; j++;
			} else {
				j++; // 여자를 더 큰 사람으로 바꾸기
			}
		}
		
		i = 0; j = 0;
		// CASE 2: boyDown-girlUp(boyHeight > girlHeight)
		Collections.sort(boyDown); Collections.sort(girlUp);
		while (i < boyDown.size() && j < girlUp.size()) {
			if (boyDown.get(i) > girlUp.get(j)) {
				ans++; i++; j++;
			} else {
				i++; // 남자를 더 큰 사람으로 바꾸기
			}
		}
		
		System.out.println(ans);
	}

}