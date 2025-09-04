import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static Map<String, Boolean> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			if (map.get(name) != null) {
				map.remove(name);
			} else {
				map.put(name, true);
			}
		}
		
		String[] keySet = map.keySet().toArray(new String[map.size()]);
		
		Arrays.sort(keySet, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		
		for (String key : keySet) {
			System.out.println(key);
		}
	}

}