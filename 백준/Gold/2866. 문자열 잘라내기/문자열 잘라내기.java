import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		String[] tempArray = new String[r];
		String[] array = new String[c];
		
		for (int i = 0; i < r; i++) {
			tempArray[i] = br.readLine();
		}

		for (int i = 0; i < c; i++) {
			String tempStr = "";
			
			for (int j = 0; j < r; j++) {
				 tempStr += tempArray[j].charAt(i);
			}
			
			array[i] = tempStr;
		}
		
		int start = 1;
		int end = r - 1;
		int max = 0;
		
		while (start <= end) {
			Set<String> set = new HashSet<>();
			int mid = (start + end) / 2;
			
			for (int i = 0; i < c; i++) {
				StringBuilder sb = new StringBuilder();
		        for (int j = mid; j < r; j++) {
		            sb.append(array[i].charAt(j));
		        }
		        set.add(sb.toString());
			}
			
			if (set.size() == c) {
		        max = mid;
		        start = mid + 1;
		    } else {
		        end = mid - 1;
		    }
		}
		
		System.out.println(max);
	}

}