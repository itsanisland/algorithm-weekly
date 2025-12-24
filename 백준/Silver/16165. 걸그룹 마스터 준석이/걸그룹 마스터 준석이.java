import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, List<String>> girlGroup = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String teamName = br.readLine();
            girlGroup.put(teamName, new ArrayList<>());
            
            int cnt = Integer.parseInt(br.readLine());
            for (int j = 0; j < cnt; j++) {
                girlGroup.get(teamName).add(br.readLine());
            }
            Collections.sort(girlGroup.get(teamName));
        }

        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            int quiz = Integer.parseInt(br.readLine());

            if (quiz == 0) {
                for (String memberName : girlGroup.get(name)) {
                    System.out.println(memberName);
                }
            } else {
                for (Map.Entry<String, List<String>> entry : girlGroup.entrySet()) {
                    List<String> team = entry.getValue();
                    if (team.contains(name)) {
                        System.out.println(entry.getKey());
                    }
                }
            }
        }
    }
}