import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            Queue<int[]> q = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                int data = Integer.parseInt(st.nextToken());
                pq.offer(data);
                q.offer(new int[] { i, data });
            }

            int cnt = 1;
            while (!q.isEmpty()) {
                int[] qPeek = q.peek();
                if (pq.peek() == qPeek[1]) {
                    if (qPeek[0] == m) {
                        System.out.println(cnt);
                        break;
                    }

                    cnt++;
                    pq.poll();
                    q.poll();
                } else {
                    q.offer(q.poll());
                }
            }
        }
    }
}