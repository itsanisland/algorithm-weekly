class Solution {
    
    private int n;
    private int[] parents; // 바로 위 부모 (root가 아님)
    
    public int solution(int n, int[][] wires) {
        int answer = n;
        this.n = n;
        parents = new int[n + 1];
        
        for (int i = 0; i < wires.length; i++) {
            for (int j = 1; j <= n; j++) {
                parents[j] = j;
            }
            
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;
                union(wires[j][0], wires[j][1]);
            }
            
            int root = find(1);
            int aCnt = 1;
            for (int j = 2; j <= n; j++) {
                if (find(j) == root) {
                    aCnt++;
                }
            }
            
            int bCnt = n - aCnt;
            answer = Math.min(answer, Math.abs(aCnt - bCnt));
        }
        
        return answer;
    }
    
    private int find(int node) { // root 찾기
        if (parents[node] == node) {
            return node;
        }
        return parents[node] = find(parents[node]);
    }
    
    private void union(int a, int b) { // 두 노드의 root끼리 연결
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA < rootB) {
            parents[rootB] = rootA;   
        } else if (rootA > rootB) {
            parents[rootA] = rootB;
        }
    }
    
}