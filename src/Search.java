import java.util.*;

/**
 * Created by eldgb on 15-Oct-16.
 */

public class Search {

    public Node initialState;
    private int[] goalState;
    private int boardSize;
    private int numberExpandedNodes = 0;
    private int maxQueueNodes = 0;

    public Search(Node initialState) {

        this.initialState = initialState;
        boardSize = (int) Math.sqrt(initialState.getState().length);
        setGoalState();
    }

    public void aStar(String heuristic) {

        if (isSolvable(initialState.getState())) {

            HashMap<String, Node> visitedNodes = new HashMap<>(); //Cointains all the nodes that have already been visited
            List<Node> newNodes;                                  //Stores all the successor nodes of the current node.
            Comparator<Node> queueComparator = new NodeCostComparator(); //Ensures that the nodes with the lowest cost are at the front of the queue
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(100, queueComparator);
            Node currentNode = initialState;
            currentNode.setGCost(0);
            if (heuristic == Heuristic.MANHATTAN)
                currentNode.setHCost(Heuristic.ManhattanDistance(initialState.getState(), boardSize));
            if (heuristic == Heuristic.MISPLACEDTILE)
                currentNode.setHCost(Heuristic.MisplacedTile(initialState.getState()));
            priorityQueue.add(currentNode);
            visitedNodes.put(currentNode.getAsString(), currentNode);

            while (!priorityQueue.isEmpty()) {

                currentNode = priorityQueue.poll();

                if (Arrays.equals(currentNode.getState(), goalState)) {

                    break;
                }

                newNodes = ExpandNodes.expandNodes(currentNode.getState()); //Expanding the nodes form the current node
                numberExpandedNodes += newNodes.size();

                for (int i = 0; i < newNodes.size(); i++) {

                    if (!visitedNodes.containsKey(newNodes.get(i).getAsString())) {//If the exapanded nodes haven't been visited, we calculate the cost and enqueue them

                        Node childNode = newNodes.get(i);
                        childNode.setParent(currentNode);
                        childNode.setGCost(childNode.getParent().getGCost() + 1);
                        childNode.setHCost(0);

                        if (heuristic == Heuristic.MANHATTAN) {

                            childNode.setHCost(Heuristic.ManhattanDistance(childNode.getState(), boardSize));
                            visitedNodes.put(childNode.getAsString(), childNode);
                            priorityQueue.add(childNode);
                        }

                        if (heuristic == Heuristic.MISPLACEDTILE) {

                            childNode.setHCost(Heuristic.MisplacedTile(childNode.getState()));
                            visitedNodes.put(childNode.getAsString(), childNode);
                            priorityQueue.add(childNode);
                        }
                    }
                }

                if (priorityQueue.size() > maxQueueNodes) maxQueueNodes = priorityQueue.size();
            }

            PrintResult.printTrace(currentNode, initialState);
            System.out.print("Goal!!!");
            PrintResult.printResult(numberExpandedNodes, maxQueueNodes, getDepth(currentNode));
        }
    }


    public void uniformSearch() { //Uniform search is exactly the same as A* search, but h(n)=0

        if (isSolvable(initialState.getState())) {

            HashMap<String, Node> visitedNodes = new HashMap<>();
            List<Node> newNodes;
            Comparator<Node> queueComparator = new NodeCostComparator();
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(100, queueComparator);
            Node currentNode = initialState;
            currentNode.setGCost(0);
            currentNode.setHCost(0);
            priorityQueue.add(currentNode);
            visitedNodes.put(currentNode.getAsString(), currentNode);

            while (!priorityQueue.isEmpty()) {

                currentNode = priorityQueue.poll();

                if (Arrays.equals(currentNode.getState(), goalState)) {

                    break;
                }

                newNodes = ExpandNodes.expandNodes(currentNode.getState());
                numberExpandedNodes += newNodes.size();

                for (int i = 0; i < newNodes.size(); i++) {

                    if (!visitedNodes.containsKey(newNodes.get(i).getAsString())) {

                        Node childNode = newNodes.get(i);
                        childNode.setParent(currentNode);
                        childNode.setGCost(childNode.getParent().getGCost() + 1);
                        childNode.setHCost(0);
                        visitedNodes.put(childNode.getAsString(), childNode);
                        priorityQueue.add(childNode);
                    }
                }

                if (priorityQueue.size() > maxQueueNodes) maxQueueNodes = priorityQueue.size();
            }

            PrintResult.printTrace(currentNode, initialState);
            System.out.print("Goal!!!");
            PrintResult.printResult(numberExpandedNodes, maxQueueNodes, getDepth(currentNode));
        }
    }

    public static boolean isSolvable(int[] state) {  //Tests if the puzzle is solvable.

        int inversion = 0;
        int value;
        int blankRow = 0;
        int boardSize = (int) Math.sqrt(state.length);

        for (int i = 0; i < state.length; i++) {

            value = state[i];
            for (int j = i + 1; j < state.length; j++) {

                if (value > state[j] && state[j] != 0) {

                    inversion += 1;
                }

                if (state[j] == 0) {

                    blankRow = Math.floorDiv(j, boardSize);
                }
            }
        }

        if (boardSize % 2 != 0 && inversion % 2 != 0) {

            System.out.print("The problem is not solvable");
            return false;
        }

        if (boardSize % 2 == 0 && (inversion + blankRow) % 2 == 0) {

            System.out.print("The problem is not solvable");
            return false;
        }

        return true;
    }

    public int getDepth(Node node) { //Calcultes the depth of the soluction

        int depth = 0;

        while (node.getParent() != null) {

            depth++;
            node = node.getParent();
        }

        return depth;
    }

    public void setGoalState() { //Sets the goal state for the 8, 15 and 25 puzzle

        switch (boardSize) {

            case 3:
                goalState = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
                break;

            case 4:
                goalState = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
                break;

            case 5:
                goalState = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0};
                break;

            default:
                System.out.println("Goal state not available");
                goalState = initialState.getState();
                break;
        }
    }
}

