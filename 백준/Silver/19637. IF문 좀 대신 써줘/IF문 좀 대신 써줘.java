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
        Type[] types = new Type[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int upper = Integer.parseInt(st.nextToken());
            types[i] = new Type(name, upper);
        }

        for (int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());
            
            // Low-bound(하한선) 이분탐색
            int left = 0, right = N - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (types[mid].upper < power) left = mid + 1;
                else right = mid - 1;
            }
            sb.append(types[left].name).append("\n");
        }
        
        System.out.println(sb);
    }
}