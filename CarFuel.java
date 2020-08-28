import java.util.Scanner;

public class CarFuel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int distance = sc.nextInt();
        int maxCanGo = sc.nextInt();
        int numberOfGasStations = sc.nextInt();
        sc.nextLine();
        String gasStationsStr = sc.nextLine();

        String [] gasStationsStrArr = gasStationsStr.split(" ");
        int [] gasStations = new int [gasStationsStrArr.length + 2];

        gasStations[0] = 0;

        for (int i = 1; i < gasStationsStrArr.length + 1; i++) {
            gasStations[i] = Integer.parseInt(gasStationsStrArr[i - 1]);
        }
        gasStations [numberOfGasStations + 1] = distance;


        System.out.println(minRefill(gasStations, numberOfGasStations, maxCanGo));


    }

    private static int minRefill(int [] stops, int numberOfStops, int maxCanGo) {
        int numRefills = 0;
        int currentRefill = 0;
        while (currentRefill <= numberOfStops) {
            int lastRefill = currentRefill;
            while (currentRefill <= numberOfStops && stops[currentRefill + 1] - stops[lastRefill] <= maxCanGo) {
                currentRefill += 1;
            }
            if (currentRefill == lastRefill) return -1;
            if (currentRefill <= numberOfStops) numRefills += 1;
        }
        return  numRefills;
    }
}