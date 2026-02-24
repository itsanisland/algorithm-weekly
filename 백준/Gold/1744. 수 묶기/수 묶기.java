import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> p = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> n = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) p.offer(num);
            if (num <= 0) n.offer(num);
        }

        int ans = 0;
        
        while (p.size() > 1) {
            int a = p.poll();
            int b = p.poll();
            if (a + b > a * b) ans += a + b;
            else ans += a * b;
        }

        while (!p.isEmpty()) ans += p.poll();

        while (n.size() > 1) {
            int a = n.poll();
            int b = n.poll();
            ans += a * b;
        }

        while (!n.isEmpty()) ans += n.poll();
        
        System.out.println(ans);
    }
}