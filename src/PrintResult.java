/**
 * Created by eldgb on 17-Oct-16.
 */
public class PrintResult {

    public static void printCurrentState(int[] currentState, int gcost, int hcost, int boardSize) {

        System.out.println("The best state to expand with a g(n) = " + gcost + " and h(n) = " + hcost + " is…");

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(currentState[j + (i * boardSize)] + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void printResult(int nodes, int queue, int depth) {

        System.out.println("To solve this problem the search algorithm expanded a total of " + nodes + " nodes.");
        System.out.println("The maximum number of nodes in the queue at any one time was " + queue);
        System.out.println("The depth of the goal node was " + depth);

    }


}
