import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int idx = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= N; i++) list.add(i);

        System.out.print("<");
        for (int i = 0; i < N; i++) {
            idx = (idx + K - 1) % list.size();
            System.out.print(list.get(idx));
            if (i < N - 1) System.out.print(", ");
            list.remove(idx);
        }
        System.out.print(">");
    }
}