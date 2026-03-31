import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    
        Queue<int[]> q = new ArrayDeque<>();
        int[] arr = new int[N];
        int t = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            q.offer(new int[]{i, cnt});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            t++;
            cur[1]--;
            
            if (cur[1] == 0) {
                arr[cur[0]] = t;
            } else {
                q.offer(cur);
            }
        }

        for (int i = 0; i < N; i++) System.out.print(arr[i] + " ");
    }
}