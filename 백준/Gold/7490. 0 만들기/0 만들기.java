import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			dfs(1, "1");
			System.out.println();
		}
	}
	
	private static void dfs(int n, String expr) {
	    if (n == N) {
	    	Queue<String> queue = new ArrayDeque<>();
	    	
	    	String numStr = "";
	    	for (char ch : expr.replace(" ", "").toCharArray()) {
	    		switch (ch) {
	    		case '+':
	    	    case '-':
	    			queue.offer(numStr);
	    			queue.offer("" + ch);
	    			numStr = "";
	    			break;
	    		default:
	    			numStr += ch;
	    		}
	    	}
	    
	    	if (!numStr.isEmpty()) {
	    		queue.offer(numStr);
	    	}
	    	
	    	int result = Integer.parseInt(queue.poll());
	    	while (!queue.isEmpty()) {
	    		String op = queue.poll();
	    		int num = Integer.parseInt(queue.poll());
	    		
	    		switch (op) {
	    		case "+":
	    			result += num;
	    			break;
	    		case "-":
	    			result -= num;
	    			break;
	    		}
	    	}
	    	
	    	if (result == 0) {
	    		System.out.println(expr);
	    	}
	    	
	        return;
	    }

	    int next = n + 1;
	    dfs(next, expr + " " + next);
	    dfs(next, expr + "+" + next);
	    dfs(next, expr + "-" + next);
	}

}