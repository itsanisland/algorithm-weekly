import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	private static String S;
	private static Set<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		
		set = new HashSet<>();
		
		for (int i = 0; i < S.length(); i++) {
			String str = "";
			for (int j = i; j < S.length(); j++) {
				str += S.charAt(j);
				set.add(str);
			}
		}

		System.out.println(set.size());
	}

}