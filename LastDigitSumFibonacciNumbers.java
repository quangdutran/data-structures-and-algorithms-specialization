import java.util.Scanner;

public class LastDigitSumFibonacciNumbers {
    public static long fibonacci_huge(long n, int m) {
        long equal_index = (n % find_fibo_period(m));

        long first = 0;
        long second = 1;

        long res = equal_index;

        for (int i = 1; i < equal_index; i++) {
            res = (first + second) % m;
            first = second;
            second = res;
        }

        return res % m;
    }

    private static int find_fibo_period(int m) {
        int a = 0;
        int b = 1;
        int c = 1;
        int limit = 1;
        for(int i = 0; i < limit; i++) {
            c = (a + b) % m;
            a = b;
            b  = c;
            if(a == 0 && b == 1) return i + 1;
            limit = limit + 1;
        }
        return limit;
    }

    private static long get_last_digit_of_fibo(long a) {
        return fibonacci_huge(a, 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();

        System.out.println(get_last_digit_of_sum_of_fibo(a));
    }

    public static long get_last_digit_of_sum_of_fibo(long n) {
        return (get_last_digit_of_fibo(n + 1) + get_last_digit_of_fibo(n - 1)) % 10;
    }
}
