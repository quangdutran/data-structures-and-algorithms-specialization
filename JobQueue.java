import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private Worker [] assignedWorkers;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    private Worker [] workers;

    private static class Worker {
        private long id;
        private long nextFreeTime;

        public Worker(long id, long nextFreeTime) {
            this.id = id;
            this.nextFreeTime = nextFreeTime;
        }
    }

    public int parentIndex(int index) {
        return (index - 1)/2;
    }

    public int leftChildIndex(int index) {
        if (index == 0) return 1; else return 2 * index + 1;
    }

    public int rightChildIndex(int index) {
        if (numWorkers % 2 == 0) return -1;
        else if (index == 0) return 2;
        else return 2 * index + 2;
    }

    public Worker assignJob(long jobTime) {
        workers[0].nextFreeTime += jobTime;
        Worker next = new Worker(workers[0].id, workers[0].nextFreeTime);
        if (workers[leftChildIndex(0)].nextFreeTime < workers[0].nextFreeTime
                || rightChildIndex(0) != -1 && workers[rightChildIndex(0)].nextFreeTime < workers[0].nextFreeTime) {
            siftDown(0);
        }
        return next;
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
        if (rightChildIn != -1 && rightChildIn <= numWorkers && workers[rightChildIn].nextFreeTime < workers[minIndex].nextFreeTime) {
            minIndex = rightChildIn;
        }
        if (index != minIndex) {
            Worker temp = workers[index];
            workers[index] = workers[minIndex];
            workers[minIndex] = temp;
            siftDown(minIndex);
        }
    }

    private void buildHeap() {
        for(int i = numWorkers / 2 - 1; i > -1; --i) {
            siftDown(i);
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
            out.println(assignedWorkers[i].id + " " + (assignedWorkers[i].nextFreeTime - jobs[i]));
        }
    }

    private void assignJobs() {
        assignedWorkers = new Worker[jobs.length];

        //Initially all threads can take jobs at the same time
        //Create heap
        workers = new Worker[numWorkers];
        for (int i = 0; i < numWorkers; i++) {
            workers[i] = new Worker(i, jobs[i]);
            assignedWorkers[i] = new Worker(i, jobs[i]);
        }
        buildHeap();

        if (jobs.length > numWorkers) {
            for (int i = numWorkers; i < jobs.length; ++i) {
                assignedWorkers[i] = assignJob(jobs[i]);
            }
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
