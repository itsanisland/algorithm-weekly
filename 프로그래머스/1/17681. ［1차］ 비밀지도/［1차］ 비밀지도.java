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
            StringBuilder sb = new StringBuilder();
            String b1 = convertToBinary(arr1[i], n);
            String b2 = convertToBinary(arr2[i], n);
            
            for (int j = 0; j < n; j++) {
                if (b1.charAt(j) == '0' && b2.charAt(j) == '0') {
                    sb.append(" ");
                } else {
                    sb.append("#");
                }
                answer[i] = sb.toString();
            }
        }

        return answer;
    }
}