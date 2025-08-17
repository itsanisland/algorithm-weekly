import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static class Node {
        // 이 노드에서 각 문자로 내려가는 간선 사용 횟수
        int[] edgeCnt = new int[26];
        // 자식 노드
        Node[] child = new Node[26];
        // 이 노드(문자열의 끝)에서 끝나는 닉네임 개수
        int finish = 0;
    }

    static Node root = new Node();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            String s = br.readLine().trim();
            out.append(process(s)).append('\n');
        }

        System.out.print(out.toString());
    }

    /**
     * 닉네임 s를 처리하여 별칭을 반환.
     * 절차:
     * 1) 트라이에 s를 삽입하며, 각 간선(edge)의 사용 횟수를 +1
     * 2) 삽입 중, 어떤 시점에서 해당 간선의 사용 횟수가 1이 되는 "가장 처음 지점"의 접두사를 기록
     * 3) 삽입 후 마지막 노드의 finish를 +1 하고,
     *    - 접두사를 찾았으면 그 접두사 반환
     *    - 못 찾았으면 finish==1 이면 s, 아니면 s + finish 반환
     */
    static String process(String s) {
        Node cur = root;
        StringBuilder prefix = new StringBuilder();
        String aliasByUniquePrefix = null; // 최단 유일 접두사

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (cur.child[idx] == null) cur.child[idx] = new Node();

            // 이 간선을 새로 사용
            cur.edgeCnt[idx]++;

            // 간선 사용 횟수가 1이 되는 "최초 순간"의 접두사를 기록
            if (aliasByUniquePrefix == null && cur.edgeCnt[idx] == 1) {
                prefix.append(s.charAt(i));
                aliasByUniquePrefix = prefix.toString();
            } else {
                prefix.append(s.charAt(i));
            }

            cur = cur.child[idx];
        }

        // s 문자열 끝에 도달
        cur.finish++;

        if (aliasByUniquePrefix != null) {
            // 최단 유일 접두사가 존재
            return aliasByUniquePrefix;
        } else {
            // 모든 접두사가 기존과 겹친 경우
            // 첫 등장이라면 원문 s, 중복이라면 s + 현재까지 등장 횟수
            if (cur.finish == 1) return s;
            return s + cur.finish;
        }
    }
}