import java.util.*;
import java.io.*;

class Main {

    static int N, i = 0;
    static String[] channels;

    static void swap(int a, int b) {
        String temp = channels[a];
        channels[a] = channels[b];
        channels[b] = temp;
    }

    static void change(String channel, int dest, BufferedWriter bw) throws IOException {
        while (!channels[i].equals(channel)) {
            i++;
            bw.append("1");
        }

        while (i != dest) {
            swap(i, i - 1);
            i--;
            bw.append("4");
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        channels = new String[N];
        
        for (int i = 0; i < N; i++) {
            channels[i] = br.readLine();
        }

        change("KBS1", 0, bw);
        change("KBS2", 1, bw);
        
        bw.flush();
        bw.close();
    }
}