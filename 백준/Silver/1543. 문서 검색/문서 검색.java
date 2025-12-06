import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String s = br.readLine() + " ";
        String word = br.readLine();
        
        System.out.println(Math.max(0, s.split(word).length - 1));
    }
}