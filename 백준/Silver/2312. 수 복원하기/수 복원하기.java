import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        boolean[] eratos = new boolean[100_001]; // 소수가 아닌 수
        eratos[0] = eratos[1] = true;

        for (int i = 2; i * i <= 100_000; i++) {
            if (eratos[i]) continue;
            for (int j = i * i; j <= 100_000; j += i) {
                eratos[j] = true;
            }
        }

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Map<Integer, Integer> map = new HashMap<>();
            
            for (int i = 2; i <= N; i++) {
                if (eratos[i]) continue;
                
                while (N % i == 0) {
                    N /= i;
                    map.put(i, map.getOrDefault(i, 0) + 1);
                }

                if (N == 1) break;
            }

            // 정렬
            List<Integer> keySet = new ArrayList(map.keySet());
            Collections.sort(keySet);

            for (int key : keySet) {
                System.out.println(key + " " + map.get(key));
            }
        }
    }
}