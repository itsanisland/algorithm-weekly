import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long N = Long.parseLong(br.readLine());
		
		while (N-- > 0) {
			int[] result = isPalindrome(br.readLine());
			int isPalindrome = result[0];
			int cnt =  result[1];
			System.out.println(isPalindrome + " " + cnt);
		}
	}
	
	private static int[] isPalindrome(String str) {
		return recursion(str, 0, str.length() - 1, 1);
	}

	private static int[] recursion(String str, int l, int r, int cnt) {
		if (l >= r) return new int[] { 1, cnt };
		else if (str.charAt(l) != str.charAt(r)) return new int[] { 0, cnt };
		else return recursion(str, l + 1, r - 1, cnt + 1);
	}

}