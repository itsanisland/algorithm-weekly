class Solution {
    private final int[] DH = {1, -1, 0, 0};
    private final int[] DW = {0, 0, -1, 1};
    
    private int N;
    
    private boolean inRange(int h, int w) {
        return 0 <= h && h < N && 0 <= w && w < N;
    }
    
    public int solution(String[][] board, int h, int w) {
        N = board.length;
        int answer = 0;
        
        for (int d = 0; d < 4; d++) {
            int nh = h + DH[d];
            int nw = w + DW[d];
            if (inRange(nh, nw) && board[nh][nw].equals(board[h][w])) {
                answer++;
            }
        }
        
        return answer;
    }
}