import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		int seq = 1;
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if (num != seq) {
				stack.push(num);
			} else {
				seq++;
			}
			
			while (!stack.isEmpty() && stack.peek() == seq) {
				stack.pop();
				seq++;
			}
		}
		
		String ans = "Nice";
		while (!stack.isEmpty()) {
			if (stack.pop() != seq) {
				ans = "Sad";
				break;
			}
			seq++;
		}
		
		System.out.println(ans);
	}

}