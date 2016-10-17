import java.util.*;

/**
 * Created by eldgb on 15-Oct-16.
 */
public class Search {

    Node initialState;
    int[] goalState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    int N = (int) Math.sqrt(goalState.length);
    int numberExpandedNodes = 0;
    int maxQueueNodes = 0;
    int depth = 0;

    public Search(Node initialState) {

        this.initialState = initialState;
    }

    public static boolean isInList(final List<int[]> list, final int[] candidate) {

        for (final int[] item : list) {
            if (Arrays.equals(item, candidate)) {
                return true;
            }
        }
        return false;
    }

    public boolean aStar(String heuristic) {

        if (isSolvable(initialState.getState())) {
            //    System.out.print(isSolvable(initialState.getState()));
            boolean solved = false;
            int cost;
            ArrayList<int[]> visitedNodes = new ArrayList<int[]>();
            List<int[]> newNodes = new ArrayList<int[]>();
            Comparator<Node> queueComparator = new NodeCostComparator();
            PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(10, queueComparator);

            Node currentNode = initialState;
            //    System.out.println(Arrays.equals(currentNode.getState(),goalState));


            while (!Arrays.equals(currentNode.getState(), goalState)) {
                depth += 1;
                visitedNodes.add(currentNode.getState());
                newNodes = ExpandNodes.expandNodes(currentNode.getState());
                numberExpandedNodes += newNodes.size();

                for (int i = 0; i < newNodes.size(); i++) {


                    if (!isInList(visitedNodes, newNodes.get(i))) {

                        visitedNodes.add(newNodes.get(i));
                        Node childNode = new Node(newNodes.get(i));


                        if (heuristic.equals(null)) {
                            childNode.setCost(0);
                            priorityQueue.add(childNode);

                        }

                        if (heuristic.equals(Heuristic.MISPLACEDTILE)) {

                            childNode.setCost(Heuristic.MisplacedTile(childNode.getState()));
                            priorityQueue.add(childNode);

                        }

                        if (heuristic.equals(Heuristic.MANHATTAN)) {

                            childNode.setCost(Heuristic.ManhattanDistance(childNode.getState(), N));
                            priorityQueue.add(childNode);

                        }


                    }


                }

                if (priorityQueue.size() > maxQueueNodes) {
                    System.out.println("Queue size: " + priorityQueue.size());
                    maxQueueNodes = priorityQueue.size();

                }

                currentNode = priorityQueue.poll();


                printCurrentState(currentNode.getState(), currentNode.getCost());


            }

            System.out.println("Goal!!!");
            printResult(numberExpandedNodes, maxQueueNodes, depth);
            return solved;
        }
        return false;

    }


    public void printCurrentState(int[] currentState, int cost) {

        System.out.println("The best state to expand with a g(n) = 1 and h(n) = " + cost + " is…");

        //currentState.replace("0", " ");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(currentState[j + (i * N)] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public void printResult(int nodes, int queue, int depth) {

        System.out.println("To solve this problem the search algorithm expanded a total of " + nodes + " nodes.");
        System.out.println("The maximum number of nodes in the queue at any one time was " + queue);
        System.out.println("The depth of the goal node was " + depth);

    }

    public static boolean isSolvable(int[] state) {

        int inversion = 0;
        int value;
        for (int i = 0; i < state.length; i++) {


            value = state[i];
            for (int j = i + 1; j < state.length; j++) {

                if (value > state[j] && state[j] != 0) {
                    inversion += 1;

                }

            }


        }
        System.out.println(inversion);
        if (inversion % 2 != 0) {
            return false;
        }
        return true;
    }
}
