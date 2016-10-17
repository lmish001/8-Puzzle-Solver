import java.util.*;

/**
 * Created by eldgb on 15-Oct-16.
 */
public class Search {

    Node initialState;
    int[] goalState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    int boardSize = (int) Math.sqrt(goalState.length);
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

    public void aStar(String heuristic) {

        if (isSolvable(initialState.getState())) {


            List<int[]> visitedNodes = new ArrayList<>();
            List<int[]> newNodes;
            Comparator<Node> queueComparator = new NodeCostComparator();
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(10, queueComparator);

            Node currentNode = initialState;



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

                            childNode.setCost(Heuristic.ManhattanDistance(childNode.getState(), boardSize));
                            priorityQueue.add(childNode);

                        }


                    }


                }

                if (priorityQueue.size() > maxQueueNodes) {
                    maxQueueNodes = priorityQueue.size();

                }

                currentNode = priorityQueue.poll();
                PrintResult.printCurrentState(currentNode.getState(), currentNode.getCost(), boardSize);


            }

            System.out.println("Goal!!!");
            PrintResult.printResult(numberExpandedNodes, maxQueueNodes, depth);

        }


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
