import java.util.Scanner;

public class MaxPrizes {
    private static void getMaxPrizes(int input) {
        int sum = 0;
        int count = 0;
        while (sum <= input) {
            count++;
            sum += count;
        }

        System.out.println(count-1);
        for (int i = 1; i <= count - 2; i++) {
            System.out.print(i + " ");
        }
        System.out.print(count - 1 + input - sum + count);


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();
        getMaxPrizes(input);
    }
}