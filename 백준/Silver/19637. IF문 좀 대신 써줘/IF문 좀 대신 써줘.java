import java.util.*;
import java.io.*;

class Main {

    static class Type {
        String name;
        int upper;

        Type(String name, int upper) {
            this.name = name;
            this.upper = upper;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Type[] types = new Type[N + 1];
        types[0] = new Type("-", -1);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int upper = Integer.parseInt(st.nextToken());
            types[i] = new Type(name, upper);
        }

        Arrays.sort(types, (a, b) -> {
            return a.upper - b.upper;
        });

        for (int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());
            // 이분탐색
            int low = 0, high = N;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (power <= types[mid].upper) {
                    if (mid > 0 && types[mid - 1].upper < power) {
                        sb.append(types[mid].name).append("\n");
                        break;
                    } else high = mid - 1;
                } else low = mid + 1;
            }
        }
        
        System.out.println(sb);
    }
}