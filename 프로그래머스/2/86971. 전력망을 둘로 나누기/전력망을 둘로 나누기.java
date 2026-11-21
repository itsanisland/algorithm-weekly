import java.util.*;

class Solution {
    
    private int n;
    private List<Integer>[] tree;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        this.n = n;
        tree = new ArrayList[n + 1];
        
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < wires.length; i++) {
            tree[wires[i][0]].add(wires[i][1]);
            tree[wires[i][1]].add(wires[i][0]);
        }
        
        // 1 ~ n까지 하나씩 끊고
        // 두 송전탑을 시작으로 각각의 전력망의 송전탑 개수 세기
        // 송전탑 개수의 차이의 최솟값 갱신
        
        for (int i = 0; i < wires.length; i++) {
            int a = bfs(wires[i][0], wires[i][1]);
            int b = bfs(wires[i][1], wires[i][0]);
            
            answer = Math.min(answer, Math.abs(a - b));
        }
        
        return answer;
    }
    
    private int bfs(int idx, int removed) {
        int cnt = 1;
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(idx);
        
        boolean[] visited = new boolean[n + 1];
        visited[idx] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int next : tree[cur]) {
                if (visited[next] || next == removed) continue;
                visited[next] = true;
                queue.offer(next);
                cnt += 1;
            }
        }
        
        return cnt;
    }
}