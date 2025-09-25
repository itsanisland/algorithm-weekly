import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Integer N = Integer.parseInt(br.readLine());
		
        StringBuilder out = new StringBuilder();
		
		drawStars(N, List.of("*"), out);

        System.out.print(out.toString());
	}
	
	private static void drawStars(int n, List<String> pattern, StringBuilder out) {
        if (n == 1) {
            for (String line : pattern) {
                out.append(line).append('\n');
            }
            return;
        }

        // 다음 패턴 생성: 위/아래는 s+s+s, 가운데는 s + 공백(len) + s
        List<String> next = new ArrayList<>(pattern.size() * 3);

        for (String s : pattern) next.add(s + s + s);                                  // 위
        for (String s : pattern) next.add(s + " ".repeat(s.length()) + s);             // 가운데
        for (String s : pattern) next.add(s + s + s);                                  // 아래

        drawStars(n / 3, next, out);
    }
}