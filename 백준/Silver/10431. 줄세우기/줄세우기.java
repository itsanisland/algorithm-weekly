 import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int P = Integer.parseInt(br.readLine());

        while (P-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int cnt = 0;
            Deque<Integer> front = new ArrayDeque<>();
            Deque<Integer> back = new ArrayDeque<>();
            
            for (int i = 0; i < 20; i++) {
                int h = Integer.parseInt(st.nextToken());

                if (front.isEmpty() || front.peekLast() < h) {
                    front.offerLast(h);
                } else {
                    while (!front.isEmpty() && front.peekLast() > h) back.offerFirst(front.pollLast());

                    cnt += back.size();
                    front.offerLast(h);

                    while (!back.isEmpty()) front.offerLast(back.pollFirst());
                }
            }

            sb.append(T + " " + cnt).append("\n");
        }

        System.out.print(sb);
    }
}