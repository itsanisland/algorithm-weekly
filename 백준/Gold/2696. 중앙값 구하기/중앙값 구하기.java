import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int m = Integer.parseInt(br.readLine());
            
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Left
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Right
            List<Integer> list = new ArrayList<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int i = 1; i <= m; i++) {
                int num = Integer.parseInt(st.nextToken());
                
                // 삽입
                if (maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.offer(num);
                else minHeap.offer(num);
                
                // 균형 맞추기
                if (maxHeap.size() > minHeap.size() + 1) minHeap.offer(maxHeap.poll());
                if (minHeap.size() > maxHeap.size()) maxHeap.offer(minHeap.poll());
            
                // 중앙값 기록 (홀수번째)
                if (i % 2 != 0) list.add(maxHeap.peek());
                
                if (i % 10 == 0) st = new StringTokenizer(br.readLine());
            }
 
            int cnt = list.size();
            System.out.println(cnt);

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= cnt; i++) {
                sb.append(list.get(i - 1)).append(" ");
                
                if (i % 10 == 0) sb.append("\n");
            }
            System.out.println(sb);
        }
    }
}