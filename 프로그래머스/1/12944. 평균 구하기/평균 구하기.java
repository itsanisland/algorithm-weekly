class Solution {
    public double solution(int[] arr) {
        double sum = 0;
        int cnt = 0;
        for (int num : arr) {
            sum += num;
            cnt++;
        }
        return sum / cnt;
    }
}