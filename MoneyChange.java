import java.util.Scanner;

public class MoneyChange {

    private static int getMinCoins(int totalValue) {
        if (totalValue - 10 < 0) {
            if (totalValue - 5 < 0 && totalValue >= 0) {
                return totalValue;
            } else {
                return 1 + getMinCoins(totalValue - 5);
            }
        } else {
            return 1 + getMinCoins(totalValue - 10);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();

        System.out.println(getMinCoins(a));
    }
}
