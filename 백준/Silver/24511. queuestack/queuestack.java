import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] type = new int[N];
        int[] val  = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) type[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) val[i] = Integer.parseInt(st.nextToken());

        // 큐(0) 자리의 초기 값들만 덱에 "등장 순서대로" 넣는다.
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) if (type[i] == 0) dq.addLast(val[i]);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());

            dq.addFirst(x);                 		// 새 값이 가장 앞을 통과
            sb.append(dq.removeLast()).append(' '); // 맨 뒤가 결과
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}