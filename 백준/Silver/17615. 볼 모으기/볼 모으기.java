import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static char[] array;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		array = st.nextToken().toCharArray();
		
		System.out.println(Math.min(move('R'), move('B')));
	}
	
	private static int move(char color) {
		int countL = 0, countR = 0;
		
		// 왼쪽부터
		int i = 0;
		while (i < N && array[i] == color) i++; // 맨 왼쪽에서부터 해당 색상이 연속되는 개수
		for (; i < N; i++) { // 위를 제외한 해당 색상의 개수 카운팅
			if (array[i] == color) countL++;
		}
		
		// 오른쪽부터
		i = N - 1;
		while (i > 0 && array[i] == color) i--; // 맨 오른쪽에서부터 해당 색상이 연속되는 개수
		for (; i >= 0; i--) { // 위를 제외한 해당 색상의 개수 카운팅
			if (array[i] == color) countR++;
		}
		
		return Math.min(countL, countR);
	}
}