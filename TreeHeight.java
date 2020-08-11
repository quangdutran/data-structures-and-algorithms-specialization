import java.util.*;
import java.io.*;

public class TreeHeight {
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

    private static class Node {
        int value;
        Node parent;

        public Node(int val) {
            value = val;
        }

        public int getHeight() {
            if (value == -1 || parent == null) {
                return 1;
            } else {
                return 1 + parent.getHeight();
            }
        }
    }

    public class Tree_Height {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {
            Node [] tree = new Node[n];
            for (int i = 0; i < n; i++) {
                Node node = new Node(i);
                tree[i] = node;
            }

            for (int i = 0; i < n; i++) {
                if (parent[i] != -1) {
                    tree[i].parent = tree[parent[i]];
                }
            }

            int maxHeight = 0;
            for (Node n : tree) {
                if (n.getHeight() > maxHeight) {
                    maxHeight = n.getHeight();
                }
            }

            return maxHeight;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new TreeHeight().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        Tree_Height tree = new Tree_Height();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
