import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int[] arr;

    static boolean canCover(int l) {
        int lastPos = 0;
        for (int i = 0; i < M; i++) {
            if (lastPos < arr[i] - l) return false;
            lastPos = arr[i] + l;
        }
        // 마지막 가로등이 굴다리 끝까지 비추는지 확인
        return lastPos >= N;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[M];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0, high = N;
        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (canCover(mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        System.out.println(ans);
    }
}