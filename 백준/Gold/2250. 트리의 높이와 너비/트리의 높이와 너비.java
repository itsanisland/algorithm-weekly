import java.util.*;
import java.io.*;

class Main {

    public static Node[] tree;
    public static int[] levelMin, levelMax;
    public static int x = 1, levelDepth = 1;
    
    public static class Node {
        int parent;
        int value;
        Node left, right;

        Node(int value) {
            this.parent = -1;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new Node[n + 1];
        levelMin = new int[n + 1];
        Arrays.fill(levelMin, n);
        levelMax = new int[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int value = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if (tree[value] == null) {
                tree[value] = new Node(value);
            }

            if (left != -1) {
                if (tree[left] == null) tree[left] = new Node(left);
                tree[left].parent = value;
                tree[value].left = tree[left];
            }

            if (right != -1) {
                if (tree[right] == null) tree[right] = new Node(right);
                tree[right].parent = value;
                tree[value].right = tree[right];
            }
        }

        // 루트 노드 찾기
        int root = -1;
        for (int i = 1; i <= n; i++) {
            if (tree[i].parent == -1) {
                root = i;
                break;
            }
        }

        inOrder(tree[root], 1);

        int maxLevel = 1, maxWidth = 1;

        for (int i = 1; i <= levelDepth; i++) {
            int width = levelMax[i] - levelMin[i] + 1;

            if (maxWidth < width) {
                maxWidth = width;
                maxLevel = i;
            }
        }

        System.out.println(maxLevel + " " + maxWidth);
    }

    public static void inOrder(Node node, int level) {
        if (node == null) return;

        levelDepth = Math.max(levelDepth, level);

        inOrder(tree[node.value].left, level + 1);

        levelMin[level] = Math.min(levelMin[level], x);
        levelMax[level] = Math.max(levelMax[level], x);
        x++; // 가로축 값(방문 순서대로 등가)
        
        inOrder(tree[node.value].right, level + 1);
    }
}