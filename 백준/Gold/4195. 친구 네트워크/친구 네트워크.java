import java.util.*;
import java.io.*;

class Main {

    private static int[] parents, size;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int f = Integer.parseInt(br.readLine());

            parents = new int[f * 2 + 1];
            size = new int[f * 2 + 1];

            for (int i = 1; i <= f * 2; i++) {
                parents[i] = i;
                size[i] = 1;
            }
            
            Map<String, Integer> map = new HashMap<>();
            int idx = 1;
            
            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();

                // mapping
                if (!map.containsKey(a)) map.put(a, idx++);
                if (!map.containsKey(b)) map.put(b, idx++);

                int root = union(map.get(a), map.get(b));
                sb.append(size[root]).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static int union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
    
        if (rootA != rootB) {
            parents[rootB] = rootA;
            size[rootA] += size[rootB];
        }
        
        return rootA;
    }
}