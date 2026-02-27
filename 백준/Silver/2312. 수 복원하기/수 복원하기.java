import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Map<Integer, Integer> map = new TreeMap<>();
            
            for (int i = 2; i * i <= N; i++) {
                while (N % i == 0) {
                    N /= i;
                    map.put(i, map.getOrDefault(i, 0) + 1);
                }
            }

            // 핵심: for 루프가 완전히 종료된 후, 아직 1보다 큰 N이 남아있다면 
            // 그 값은 N의 가장 큰 마지막 소인수
            if (N > 1) {
                map.put(N, map.getOrDefault(N, 0) + 1);
            }

            for (int key : map.keySet()) {
                System.out.println(key + " " + map.get(key));
            }
        }
    }
}