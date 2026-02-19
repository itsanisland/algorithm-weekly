import java.util.*;
import java.io.*;

class Main {

    static int N, ans = Integer.MIN_VALUE;
    static int[] nums;
    static char[] ops;

    static int calc(int num1, int num2, char op) {
        switch(op) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
        }
        return 0;
    }

    // idx: 연산자 인덱스(연산자 위치 기준 괄호 여부 선택)
    // val: 현재까지 계산된 값
    static void dfs(int idx, int val) {
        // 모든 연산자를 사용했으면 종료
        if (idx == ops.length) {
            ans = Math.max(ans, val);
            return;
        }

        // 괄호 X
        // 그냥 계산
        int val1 = calc(val, nums[idx + 1], ops[idx]);
        dfs(idx + 1, val1);
        
        // 괄호 O
        // 다음 연산 먼저 계산
        if (idx + 1 < ops.length) {
            int temp = calc(nums[idx + 1], nums[idx + 2], ops[idx + 1]);
            int val2 = calc(val, temp, ops[idx]);
            dfs(idx + 2, val2);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        nums = new int[N / 2 + 1];
        ops = new char[N / 2];

        for (int i = 0; i < N / 2; i++) {
            nums[i] = s.charAt(i * 2) - '0';
            ops[i] = s.charAt(i * 2 + 1);
        }

        nums[N / 2] = s.charAt(N - 1) - '0';

        dfs(0, nums[0]);
        
        System.out.println(ans);
    }
}