import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] scores = new int[n + 1][k + 1]; // 각 팀의 각 문제에 대한 점수
            int[] submitCnts = new int[n + 1]; // 제출횟수
            int[] lastSubmits = new int[n + 1]; // 마지막 제출 시간
            int[] totalScores = new int[n + 1]; // 총점
            int rank = 1;
            
            for (int l = 0; l < m; l++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                scores[i][j] = Math.max(scores[i][j], s);
                submitCnts[i] += 1;
                lastSubmits[i] = l;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    totalScores[i] += scores[i][j];
                }
            }

            for (int i = 1; i <= n; i++) {
                if (i == t) continue;

                if (totalScores[i] > totalScores[t]) rank += 1;
                else if (totalScores[i] == totalScores[t]) {
                    if (submitCnts[i] < submitCnts[t]) rank += 1;
                    else if (submitCnts[i] == submitCnts[t]) {
                        if (lastSubmits[i] < lastSubmits[t]) rank += 1;
                    }
                }
            }

            System.out.println(rank);
        }
    }
}