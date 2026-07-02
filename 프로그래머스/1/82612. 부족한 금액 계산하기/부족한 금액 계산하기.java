class Solution {
    public long solution(int price, int money, int count) {
        long increasedPrice = (long) price * ((1 + count) * count / 2);
        long answer = increasedPrice - money;
        return Math.max(0, answer);
    }
}