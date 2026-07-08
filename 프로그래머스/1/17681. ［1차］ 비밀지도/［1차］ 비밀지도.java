class Solution {
    
    private String convertToBinary(int decimal, int n) {
        StringBuilder sb = new StringBuilder();
        
        while (decimal > 0) {
            sb.append(decimal % 2);
            decimal /= 2;
        }
        
        int length = n - sb.length();
        
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        
        return sb.reverse().toString();
    }
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            String binary = convertToBinary(arr1[i] | arr2[i], n);
            answer[i] = binary.replaceAll("0", " ").replaceAll("1", "#");
        }

        return answer;
    }
}