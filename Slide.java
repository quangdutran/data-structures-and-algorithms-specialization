import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Slide {
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
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = scanner.nextInt();
        int window = scanner.nextInt();
        Queue<Integer> integerQueue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            integerQueue.add(a[i]);
            if (i == window - 1) {
                System.out.println(Collections.max(integerQueue));
            }

            if (i >= window) {
                integerQueue.poll();
                System.out.println(Collections.max(integerQueue));
            }

        }
    }

    static public void main(String[] args) throws IOException {
        new Slide().solve();
    }
}
