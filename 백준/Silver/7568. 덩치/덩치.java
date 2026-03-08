import java.util.*;
import java.io.*;

class Main {

    static class Size {
        int w, h;

        Size(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }

    static int N;
    static Size[] arr;

    static int getRank(int idx) {
        int rank = 1;
        for (int i = 0; i < N; i++) {
            if (arr[i].w > arr[idx].w && arr[i].h > arr[idx].h) rank++;
        }
        return rank;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        arr = new Size[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            arr[i] = new Size(w, h);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(getRank(i) + " ");
        }
    }
}