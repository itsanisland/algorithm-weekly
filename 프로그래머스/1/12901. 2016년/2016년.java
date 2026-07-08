class Solution {

    int[] daysOfMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 4, 7, 9, 11
    String[] dayOfWeekend = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        
    public String solution(int a, int b) {
        int days = b;
        for (int i = 1; i < a; i++) {
            days += daysOfMonth[i];
        }
        return dayOfWeekend[days % 7];
    }
}