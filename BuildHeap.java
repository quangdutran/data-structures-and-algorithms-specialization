import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private static int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation
      for (int i = data.length / 2 - 1; i > -1; --i) {
          shiftDown(i);
      }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    public int parentIndex(int index) {
        return (index - 1)/2;
    }

    public int leftChildIndex(int index) {
        if (index == 0) return 1; else return 2 * index + 1;
    }

    public int rightChildIndex(int index) {
        if (index == 0) return 2; else return 2 * index + 2;
    }

    public void shiftDown(int index) {
        int minIndex = index;
        int leftChildIn = leftChildIndex(index);
        if (leftChildIn <= data.length && data[leftChildIn] < data[minIndex]) {
            minIndex = leftChildIn;
        }
        int rightChildIn = rightChildIndex(index);
        if (rightChildIn <= data.length && data[rightChildIn] < data[minIndex]) {
            minIndex = rightChildIn;
        }
        if (index != minIndex) {
            int temp = data[index];
            data[index] = data[minIndex];
            data[minIndex] = temp;
            swaps.add(new Swap(index, minIndex));
            shiftDown(minIndex);
        }
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
