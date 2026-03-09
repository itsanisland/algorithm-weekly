import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<String, Integer> map = new HashMap<>() {{
            put("Y", 1);
            put("F", 2);
            put("O", 3);
        }};
        
        int N = Integer.parseInt(st.nextToken());
        String game = st.nextToken();
        Set<String> set = new HashSet<>();
        int ans = 0;
        int numsOfPlayers = map.get(game);

        while (N-- > 0) {
            String name = br.readLine();
            set.add(name);
        }
        
        System.out.println(set.size() / numsOfPlayers);
    }
}