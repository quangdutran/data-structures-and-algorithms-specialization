import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeTraversal {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class Node {
        private Node parent;
        private Node right;
        private Node left;
        private int value;

        public Node(Node parent, Node right, Node left, int value) {
            this.parent = parent;
            this.right = right;
            this.left = left;
            this.value = value;
        }

        public Node(int value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public class TreeOrders {
        int n;
        int[] left, right;

        Node [] nodes;

        int indexRoot = -1;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            left = new int[n];
            right = new int[n];
            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(in.nextInt());
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }

            for (int i = 0; i < n; i++) {
                if (left[i] != -1) {
                    nodes[i].left = nodes[left[i]];
                    nodes[left[i]].parent = nodes[i];
                }
                if (right[i] != -1) {
                    nodes[i].right = nodes[right[i]];
                    nodes[right[i]].parent = nodes[i];
                }
            }

            for (int i = 0; i < n; i++) {
                if (nodes[i].parent == null) indexRoot = i;
            }
        }

        void inOderTraversal(Node node) {
            if (node == null) {
                return;
            }
            inOderTraversal(node.left);
            System.out.print(node.value + " ");
            inOderTraversal(node.right);
        }

        void inOrder() {
            inOderTraversal(nodes[indexRoot]);
            System.out.println();
        }

        void preOderTraversal(Node node) {
            if (node == null) {
                return;
            }
            System.out.print(node.value + " ");
            preOderTraversal(node.left);
            preOderTraversal(node.right);
        }

        void preOrder() {
            preOderTraversal(nodes[indexRoot]);
            System.out.println();
        }

        void postOderTraversal(Node node) {
            if (node == null) {
                return;
            }
            postOderTraversal(node.left);
            postOderTraversal(node.right);
            System.out.print(node.value + " ");
        }

        void postOrder() {
            postOderTraversal(nodes[indexRoot]);
            System.out.println();
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new TreeTraversal().run();
            } catch (IOException ignored) {
            }
        }, "1", 1 << 26).start();
    }


    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        tree.inOrder();
        tree.preOrder();
        tree.postOrder();
    }
}
