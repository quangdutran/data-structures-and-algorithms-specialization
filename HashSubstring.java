import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    //Find the largest possible that greater than |T||P| -- in this case could be 25*10^10
    private static final long prime = 299999999989L;
    private static final long multiplier = 1 + (long) (Math.random() * (prime - 1));


    private static long polyHash(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return hash;
    }

    private static long [] preComputeHashes(String text, int lengthPattern) {
        long [] result = new long [text.length() - lengthPattern + 1];
        String lastPiece = text.substring(text.length() - lengthPattern, text.length() - 1);
        result[text.length() - lengthPattern] = polyHash(lastPiece);
        long y = 1;
        for (int i = 0; i <= lengthPattern; i++) {
            y = (y * multiplier) % prime;
        }
        for (int i = text.length() - lengthPattern - 1; i >= 0; i--) {
            result[i] = (multiplier * result[i + 1] + text.charAt(i) - y * text.charAt(i + lengthPattern)) % prime;

        }
        return result;
    }

    private static List<Integer> rabinKarp(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        long patternHash = polyHash(pattern);
        long [] preComputeHashes = preComputeHashes(text, pattern.length());
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (patternHash != preComputeHashes[i])
                continue;
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        return rabinKarp(s, t);
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

