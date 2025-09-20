import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>();
		
		while (N-- > 0) {
			String word = br.readLine();
			
			if (word.length() >= M) {
				map.put(word, map.getOrDefault(word, 0) + 1);
			}
		}
		
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		
		Collections.sort(list, new Comparator<>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (o1.getValue() == o2.getValue()) {
					if (o1.getKey().length() == o2.getKey().length()) {
						return o1.getKey().compareTo(o2.getKey());
					}
					
					return Integer.compare(o2.getKey().length(), o1.getKey().length());
				}
				
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<String, Integer> entryMap : list) {
			sb.append(entryMap.getKey()).append("\n");
		}
		
		System.out.println(sb);
	}

}