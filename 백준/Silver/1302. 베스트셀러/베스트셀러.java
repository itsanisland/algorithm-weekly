import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String book = br.readLine();
            map.put(book, map.getOrDefault(book, 0) + 1);    
        }

        List<String> list = new ArrayList(map.keySet());

        Collections.sort(list, new Comparator<String>() {

            public int compare(String s1, String s2) {
                if (map.get(s1) == map.get(s2)) return s1.compareTo(s2);
                return map.get(s2) - map.get(s1);
            }
        });

        System.out.println(list.get(0));
    }
}