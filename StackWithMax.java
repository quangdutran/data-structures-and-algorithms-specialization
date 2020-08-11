import java.util.*;
import java.io.*;

public class StackWithMax {
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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        MyStack stack = new MyStack();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
            } else if ("pop".equals(operation)) {
                stack.pop();
            } else if ("max".equals(operation)) {
                System.out.println(stack.max());
            }
        }
    }

    private static class MyStack {
        Stack<Integer> mainStack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();

        public void push(int value) {
            mainStack.push(value);
            if (maxStack.empty() || maxStack.peek() < value) {
                maxStack.push(value);
            } else {
                maxStack.push(maxStack.peek());
            }
        }

        public int pop() {
            maxStack.pop();
            return mainStack.pop();
        }

        public int max() {
            return maxStack.peek();
        }


    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}