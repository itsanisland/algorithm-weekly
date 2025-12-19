import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());  

        PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> negative = new PriorityQueue<>(Collections.reverseOrder());

        int max = 0;

        for (int i = 0; i < n; i++) {
            int book = Integer.parseInt(st.nextToken());
            if (book < 0) {
                negative.offer(-1 * book);
            } else {
                positive.offer(book);
            }
            max = Math.max(max, Math.abs(book));
        }

        int ans = 0;

        while (!positive.isEmpty()) {
            int subMax = positive.poll();
            for (int i = 0; i < m - 1; i++) {
                if (positive.peek() != null) {
                    positive.poll();
                }
            }
            ans += subMax * 2;
        }

        while (!negative.isEmpty()) {
            int subMax = negative.poll();
            for (int i = 0; i < m - 1; i++) {
                if (negative.peek() != null) {
                    negative.poll();
                }
            }
            ans += subMax * 2;
        }

        System.out.println(ans - max);
    }
}