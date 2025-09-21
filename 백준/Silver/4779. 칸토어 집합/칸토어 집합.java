import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		
		while ((line = br.readLine()) != null) {
			line = line.trim();
            if (line.isEmpty()) continue;
            
			int N = Integer.parseInt(line);
			
			int size = (int) Math.pow(3, N);

            System.out.println(recursive(size));
		}
	
	}
	
	private static String recursive(int n) {
		if (n <= 1) return "-";
		
		String line = recursive(n / 3);
		
		return line + " ".repeat(n / 3) + line;
	}

}