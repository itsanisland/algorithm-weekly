import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> q = new ArrayDeque<>();

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("push")) {
                int X = Integer.parseInt(st.nextToken());
                q.offer(X);
            } else if (cmd.equals("pop")) {
                System.out.println(q.isEmpty() ? -1 : q.poll());
            } else if (cmd.equals("size")) {
                System.out.println(q.size());
            } else if (cmd.equals("empty")) {
                System.out.println(q.isEmpty() ? 1 : 0);
            } else if (cmd.equals("front")) {
                System.out.println(q.isEmpty() ? -1 : q.peekFirst());
            } else {
                System.out.println(q.isEmpty() ? -1 : q.peekLast());
            }
        }
    }
}