class Solution {
    
    int time, h;
    int MAX_HEALTH;
    
    private void doHealing(int[] bandage, int start, int end) {
        for (int i = start; i < end; i++) {
            time++;
            h = Math.min(h + bandage[1], MAX_HEALTH);
            if (time == bandage[0]) {
                time = 0;
                h = Math.min(h + bandage[2], MAX_HEALTH);
            }
        }
    }
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        time = 0;
        h = health;
        MAX_HEALTH = health;
        int prev = 1;
        
        for (int i = 0; i < attacks.length; i++) {
            doHealing(bandage, prev, attacks[i][0]);
            h -= attacks[i][1];
            if (h <= 0) {
                return -1;
            }
            time = 0;
            prev = attacks[i][0] + 1;
        }
        
        return h;
    }
}