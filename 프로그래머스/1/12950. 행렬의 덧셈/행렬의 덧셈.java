class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int n = arr1.length, m = arr1[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr1[i][j] += arr2[i][j];
            }
        }
        return arr1;
    }
}