class Solution {
    
    private int n, answer;
    
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        
        explore(0, new boolean[n], k, dungeons);
        
        return answer;
    }
    
    // 순열
    private void explore(int cnt, boolean[] selected, int k, int[][] dungeons) {        
        if (answer == n) return;
        
        answer = Math.max(answer, cnt); 
        
        for (int i = 0; i < n; i++) {
            if (!selected[i] && dungeons[i][0] <= k) {
                selected[i] = true;
                explore(cnt + 1, selected, k - dungeons[i][1], dungeons);
                selected[i] = false;
            }
        }
    }
}