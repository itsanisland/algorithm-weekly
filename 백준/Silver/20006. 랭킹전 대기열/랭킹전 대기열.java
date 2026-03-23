import java.util.*;
import java.io.*;

class Main {

    static class Player {
        int l;
        String n;
        Player(int l, String n) {
            this.l = l;
            this.n = n;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Player>> list = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();

            boolean entered = false;
            for (int j = 0; j < list.size(); j++) {
                int diff = Math.abs(list.get(j).get(0).l - l);
                
                if (diff <= 10 && list.get(j).size() < m) {
                    list.get(j).add(new Player(l, n));
                    entered = true;
                    break;
                }
            }

            if (!entered) {
                List<Player> temp = new ArrayList<>();
                temp.add(new Player(l, n));
                list.add(temp);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).size() == m ? "Started!" : "Waiting!").append("\n");

            Collections.sort(list.get(i), (a, b) -> {
               return a.n.compareTo(b.n);
            });
            
            for (Player player : list.get(i)) {
                sb.append(player.l + " " + player.n).append("\n");
            }
        }
        
        System.out.println(sb);
    }
}