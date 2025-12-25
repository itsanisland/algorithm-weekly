import java.util.*;
import java.io.*;

class Main {

    public static class Question implements Comparable<Question> {
        int sub1, sub2;

        Question(int sub1, int sub2) {
            this.sub1 = sub1;
            this.sub2 = sub2;
        }

        public int compareTo(Question o) {
            return this.sub2 - o.sub2;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Question> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            int sub1 = Integer.parseInt(st.nextToken());
            int sub2 = Integer.parseInt(st.nextToken());

            pq.offer(new Question(sub1, sub2));
        }

        int ans = 0;
        while (k > 0 && !pq.isEmpty()) {
            Question q = pq.poll();
            if (q.sub2 <= l) {
                ans += 140;
                k--;
            } else if (q.sub1 <= l) {
                ans += 100;
                k--;
            }
        }
        
        System.out.println(ans);
    }
}