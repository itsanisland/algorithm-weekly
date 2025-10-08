class Solution {
    int N = 0, ans = 0;
    int[] selected;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        selected = new int[N];
        visited = new boolean[N];
        
        perm(0, N, k, dungeons);
    
        return ans;
    }
    
    private void perm(int depth, int r, int k, int[][] dungeons) {
        if (depth == r) {
            int cnt = 0;
            for (int i : selected) {
                int base = dungeons[i][0], using = dungeons[i][1];
                
                if (k >= base) {
                    k -= using;
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = i;
                perm(depth + 1, r, k, dungeons);
                visited[i] = false;
            }
        }
    }
}