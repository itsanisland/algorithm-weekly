class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;
        int width = 1;
        int height = total;
        
        while (true) {
            if (width >= height && width * height == total && brown == 2 * (width + (height - 2)) && yellow == (width - 2) * (height - 2)) {
                break;
            }
            width++;
            height = total / width;
        }
        
        int[] answer = {width, height};
        return answer;
    }
}