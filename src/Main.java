import java.util.Scanner;

/**
 * Created by eldgb on 15-Oct-16.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Lisaveta Mishkinitse’s 8-puzzle solver.\n" +
                "Type “1” to use a default puzzle, or “2” to enter your own puzzle.");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

        if (i == 1) {

            int[] state = {8, 6, 7, 2, 5, 4, 3, 0, 1};

            Node initialState = new Node(state);
            Search search = new Search(initialState);
            System.out.println("Enter your choice of algorithm\n" +
                    "1. Uniform Cost Search\n" +
                    "2. A* with the Misplaced Tile heuristic.\n" +
                    "3. A* with the Manhattan distance heuristic.\n");
            int j = sc.nextInt();

            switch (j) {
                case 1:
                    search.uniformSearch();
                    break;
                case 2:
                    search.aStar(Heuristic.MISPLACEDTILE);
                    break;
                case 3:
                    search.aStar(Heuristic.MANHATTAN);
                    break;
            }
        }

        if (i == 2) {

            int[] state = new int[9];
            System.out.println("Enter your puzzle, use a zero to represent the blank\n" +
                    "Enter the first row, use space between numbers");
            for (int j = 0; j < 3; j++) {
                state[j] = sc.nextInt();
            }

            System.out.println("Enter the second row, use space between numbers");
            for (int j = 3; j < 6; j++) {
                state[j] = sc.nextInt();
            }

            System.out.println("Enter the third row, use space between numbers");
            for (int j = 6; j < 9; j++) {
                state[j] = sc.nextInt();
            }

            Node initialState = new Node(state);
            Search search = new Search(initialState);
            System.out.println("Enter your choice of algorithm\n" +
                    "1. Uniform Cost Search\n" +
                    "2. A* with the Misplaced Tile heuristic.\n" +
                    "3. A* with the Manhattan distance heuristic.\n");
            int j = sc.nextInt();

            switch (j) {
                case 1:
                    search.uniformSearch();
                    break;
                case 2:
                    search.aStar(Heuristic.MISPLACEDTILE);
                    break;
                case 3:
                    search.aStar(Heuristic.MANHATTAN);
                    break;

            }
        }
        sc.close();
    }
}



