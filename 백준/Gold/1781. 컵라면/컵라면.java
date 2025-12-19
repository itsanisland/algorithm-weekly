import java.util.*;
import java.io.*;

class Main {
    public static class Question implements Comparable<Question> {
        int deadline, cnt;

        Question(int deadline, int cnt) {
            this.deadline = deadline;
            this.cnt = cnt;
        }

        public int compareTo(Question o) {
            return this.deadline - o.deadline;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        Question[] questions = new Question[n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            questions[i] = new Question(deadline, cnt);
        }

        Arrays.sort(questions);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Question q : questions) {
            pq.offer(q.cnt);
            if (pq.size() > q.deadline) {
                pq.poll();
            }
        }
        
        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }

        System.out.println(ans);
    }
}