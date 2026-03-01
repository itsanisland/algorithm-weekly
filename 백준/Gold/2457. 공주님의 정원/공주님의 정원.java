import java.util.*;
import java.io.*;

class Main {

    static class Flower implements Comparable<Flower> {
        int start, end;
        
        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Flower o) {
            if (this.start == o.start) return o.end - this.end;
            return this.start - o.start;
        }
    }

    static final int START = 301, END = 1130;

    static int convert(String m, String d) {
        return Integer.parseInt(m) * 100 + Integer.parseInt(d);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        List<Flower> flowers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = convert(st.nextToken(), st.nextToken());
            int end = convert(st.nextToken(), st.nextToken());

            if (end < START || start > END) continue;
            flowers.add(new Flower(start, end));
        }

        Collections.sort(flowers);

        int target = START; // 현재 심을 수 있는 꽃 선택 기준
        int maxEnd = START; // 현재 심을 수 있는 꽃 중 가장 멀리 필 수 있는 꽃 선택 기준
        int idx = 0, ans = 0;

        // 현재 내가 심은 꽃 기준, 그 시점에서 이어질 수 있는 꽃 중, 가장 멀리까지 피어있는 꽃 선택(그리디)
        while (target <= END) {
            boolean found = false;

            // target 이전에 피는 꽃 중 가장 늦게 지는 꽃 선택
            while (idx < flowers.size() && flowers.get(idx).start <= target) {
                if (flowers.get(idx).end > maxEnd) {
                    maxEnd = flowers.get(idx).end;
                    found = true;
                }
                idx++;
            }

            if (found) {
                target = maxEnd; // 기준점 업데이트
                ans++;
            } else {
                ans = 0;
                break; // 더 이상 이을 수 있는 꽃 없음
            }
        }

        System.out.println(ans);
    }
}