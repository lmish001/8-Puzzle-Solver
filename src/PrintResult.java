/**
 * Created by eldgb on 17-Oct-16.
 */

import java.util.ArrayList;
import java.util.List;

public class PrintResult {


    public static void printResult(int nodes, int queue, int depth) {

        System.out.println("To solve this problem the search algorithm expanded a total of " + nodes + " nodes.");
        System.out.println("The maximum number of nodes in the queue at any one time was " + queue);
        System.out.println("The depth of the goal node was " + depth);
    }

    public static void printArray(int[] array) {

        int boardSize = (int) Math.sqrt(array.length);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(array[j + (i * boardSize)] + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void printTrace(Node node, Node rootNode) {

        List<Node> aux = new ArrayList<>();

        while (node.getParent() != null) {

            aux.add(node);
            node = node.getParent();
        }

        System.out.println("Expanding state \n");
        printArray(rootNode.getState());

        for (int i = aux.size() - 1; i >= 0; i--) {

            System.out.println("The best state to expand with a g(n) = " + aux.get(i).getGCost() + " and h(n) = " + aux.get(i).getHCost() + " is…");
            printArray(aux.get(i).getState());
        }
    }
}
