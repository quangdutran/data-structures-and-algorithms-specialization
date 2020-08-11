import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    private Worker [] workers;

    private static class Worker {
        private long nextFreeTime;
        private int job;

        public Worker(long nextFreeTime, int job) {
            this.nextFreeTime = nextFreeTime;
            this.job = job;
        }
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

    public void siftUp(int index) {
        while (index > 0 && workers[index].nextFreeTime < workers[parentIndex(index)].nextFreeTime) {
            Worker temp = workers[parentIndex(index)];
            workers[parentIndex(index)] = workers[index];
            workers[index] = temp;
            index = parentIndex(index);

        }
    }

    public void siftDown(int index) {
        int minIndex = index;
        int leftChildIn = leftChildIndex(index);
        if (leftChildIn <= numWorkers && workers[leftChildIn].nextFreeTime < workers[minIndex].nextFreeTime) {
            minIndex = leftChildIn;
        }
        int rightChildIn = rightChildIndex(index);
        if (rightChildIn <= numWorkers && workers[rightChildIn].nextFreeTime < workers[minIndex].nextFreeTime) {
            minIndex = rightChildIn;
        }
        if (index != minIndex) {
            Worker temp = workers[index];
            workers[index] = workers[minIndex];
            workers[minIndex] = temp;
            siftDown(minIndex);
        }
    }

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
