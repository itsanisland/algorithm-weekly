import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] A = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);

        for (int i = 0; i < N; i++) {
            long cur = pq.poll();

            for (int j = 0; j < K; j++) {
                long next = cur * A[j];
                pq.offer(next);

                // 중복 생성 차단 -> 메모리 초과 방지
                if (cur % A[j] == 0) break;
            }
        }

        System.out.println(pq.poll());
    }
}