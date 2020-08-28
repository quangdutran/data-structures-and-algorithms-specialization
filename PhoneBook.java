import java.io.*;
import java.util.*;

/**
 * Solution is to use direct addressing scheme
 * which has access time O(1)
 */
public class PhoneBook {

    //private FastScanner in;
    Scanner in = new Scanner(System.in);
    private PrintWriter out;

    //Max length of phone number is 7, at least one action is not adding new contact
    private String[] contacts = new String[9999999 - 1];

    public static void main(String[] args) throws IOException {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void processQuery(Query query) {
        if (query.type.equals("add")) {
            contacts[query.number] = query.name;
        } else if (query.type.equals("del")) {
            if (contacts[query.number] != null)
                contacts[query.number] = null;
        } else {
            out.println(contacts[query.number] == null ? "not found" : contacts[query.number]);
        }
    }

    public void processQueries() throws IOException {
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
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
