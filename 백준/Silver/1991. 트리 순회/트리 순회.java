import java.util.*;
import java.io.*;

class Main {

    public static Node[] tree;
    
    public static class Node {
        char data;
        Node left, right;

        Node(char data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new Node[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char data = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (tree[data - 'A'] == null) {
                tree[data - 'A'] = new Node(data);
            }

            if (left != '.') {
                tree[left - 'A'] = new Node(left);
                tree[data - 'A'].left = tree[left - 'A'];
            }

            if (right != '.') {
                tree[right - 'A'] = new Node(right);
                tree[data - 'A'].right = tree[right - 'A'];
            }
        }
        
        preOrder(tree[0]);
        System.out.println();

        inOrder(tree[0]);
        System.out.println();

        postOrder(tree[0]);
        System.out.println();
    }

    public static void preOrder(Node node) {
        System.out.print(node.data);

        if (tree[node.data - 'A'].left != null) {
            preOrder(tree[node.data - 'A'].left);
        }

        if (tree[node.data - 'A'].right != null) {
            preOrder(tree[node.data - 'A'].right);
        }
    }

    public static void inOrder(Node node) {
        if (tree[node.data - 'A'].left != null) {
            inOrder(tree[node.data - 'A'].left);
        }

        System.out.print(node.data);

        if (tree[node.data - 'A'].right != null) {
            inOrder(tree[node.data - 'A'].right);
        }
    }

    public static void postOrder(Node node) {
        if (tree[node.data - 'A'].left != null) {
            postOrder(tree[node.data - 'A'].left);
        }

        if (tree[node.data - 'A'].right != null) {
            postOrder(tree[node.data - 'A'].right);
        }

        System.out.print(node.data);
    }
}