import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            Map<Integer, Integer> map = new HashMap<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            }

            Set<Integer> set = new HashSet<>();
            for (int key : map.keySet()) {
                if (map.get(key) == 6) set.add(key);
            }

            List<Integer>[] ranks = new ArrayList[map.size() + 1];
            for (int i = 1; i <= map.size(); i++) ranks[i] = new ArrayList<>();
            
            int rank = 1;
            for (int i = 0; i < N; i++) {
                if (set.contains(arr[i])) {
                    ranks[arr[i]].add(rank++);
                }
            }

            int teamIdx = 0;
            int maxSum = Integer.MAX_VALUE;
            
            for (int i = 1; i <= map.size(); i++) {
                if (ranks[i].isEmpty()) continue;
                
                int sum = 0;
                for (int j = 0; j < 4; j++) sum += ranks[i].get(j);

                if (sum < maxSum) {
                    maxSum = sum;
                    teamIdx = i;
                } else if (sum == maxSum) {
                    if (ranks[i].get(4) < ranks[teamIdx].get(4)) {
                        teamIdx = i;
                    }
                }
            }

            System.out.println(teamIdx);
        }
    }
}