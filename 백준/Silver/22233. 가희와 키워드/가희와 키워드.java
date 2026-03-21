import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> memo = new HashSet<>();

        for (int i = 0; i < N; i++) {
            memo.add(br.readLine());
        }

        while (M-- > 0) {
            String[] keywords = br.readLine().split(",");
            for (String keyword : keywords) {
                if (memo.contains(keyword)) {
                    memo.remove((keyword));
                }
            }
            sb.append(memo.size() + "\n");
        }

        System.out.println(sb);
    }
}