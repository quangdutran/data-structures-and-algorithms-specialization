import java.util.Scanner;

public class FibonacciLastDigit {
    public static int fibonacci_last_digit(int n) {
        if (n <= 1) return n;
        int [] numbers = new int [n];
        numbers[0] = 1;
        numbers[1] = 1;
        for (int i = 2; i < n; i++) {
            numbers[i] = (numbers[i - 1] % 10 + numbers[i -2] % 10)%10;
        }

        return numbers[n - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = fibonacci_last_digit(n);
        System.out.println(c);
    }
}
