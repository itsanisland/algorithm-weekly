import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = true;

        Stack<Integer> stack = new Stack();
        int cur = 0;
        int i = 1;

        while (i <= n) {
            if (i == arr[cur]) {
                sb.append("+").append("\n");
                sb.append("-").append("\n");
                cur++;
                i++;
            } else if (i < arr[cur]) {
                stack.push(i);
                sb.append("+").append("\n");
                i++;
            } else {
                if (stack.peek() != arr[cur]) {
                    flag = false;
                    break;
                }
                stack.pop();
                sb.append("-").append("\n");
                cur++;
            }
        }
        
        while(!stack.isEmpty()) {
            if (stack.peek() != arr[cur]) {
                flag = false;
                break;
            }
            stack.pop();
            sb.append("-").append("\n");
            cur++;
        }

        System.out.println(flag ? sb : "NO");
    }
}