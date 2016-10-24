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

            List<int[]> visitedNodes = new ArrayList<>(); //An array list that cointains all the nodes that have already been visited
            List<int[]> newNodes;  //An array list that will store all the successor nodes of the current node.
            Comparator<Node> queueComparator = new NodeCostComparator(); //A comparator for the priority queue to ensure that we enqueue the node with the least cost.
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(10, queueComparator);
            Node currentNode = initialState;  //the root node is the initial state of the puzzle
            currentNode.setGCost(0);

            while (!Arrays.equals(currentNode.getState(), goalState)) {
                visitedNodes.add(currentNode.getState());
                newNodes = ExpandNodes.expandNodes(currentNode.getState()); //Gets all the succesors of the current node
                numberExpandedNodes += newNodes.size();

                for (int i = 0; i < newNodes.size(); i++) {

                    if (!isInList(visitedNodes, newNodes.get(i))) {// We don't enqueue a node if it has already been visited

                        visitedNodes.add(newNodes.get(i)); // If the node hasn't been visited, we add it to the visited nodes list.
                        Node childNode = new Node(newNodes.get(i));
                        childNode.setParent(currentNode);

                        if (heuristic.equals(Heuristic.MISPLACEDTILE)) {

                            childNode.setHCost(Heuristic.MisplacedTile(childNode.getState())); //We calculate the distance to the goal state
                            childNode.setGCost(childNode.getParent().getGCost() + 1); //the cost to the next state is always 1
                            priorityQueue.add(childNode); //We add the node to the priority queue depending on the cost

                        }

                        if (heuristic.equals(Heuristic.MANHATTAN)) {

                            childNode.setHCost(Heuristic.ManhattanDistance(childNode.getState(), boardSize));
                            childNode.setGCost(childNode.getParent().getGCost() + 1);
                            priorityQueue.add(childNode);

                        }
                    }
                }

                if (priorityQueue.size() > maxQueueNodes) {
                    maxQueueNodes = priorityQueue.size();

                }

                currentNode = priorityQueue.poll(); // the new current node is the first node to dequeue from the queue
                PrintResult.printCurrentState(currentNode.getState(), currentNode.getGCost(), currentNode.getHCost(), boardSize);

            }

            System.out.println("Goal!!!");
            PrintResult.printResult(numberExpandedNodes, maxQueueNodes, getDepth(currentNode));

        }
    }

    public void uniformSearch() { //It is identical to aStar search, except for the costs. h_cost is hardcoded to 0.

        if (isSolvable(initialState.getState())) {


            List<int[]> visitedNodes = new ArrayList<>();
            List<int[]> newNodes;
            Comparator<Node> queueComparator = new NodeCostComparator();
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(10, queueComparator);
            Node currentNode = initialState;
            currentNode.setGCost(0);
            currentNode.setHCost(0);


            while (!Arrays.equals(currentNode.getState(), goalState)) {
                visitedNodes.add(currentNode.getState());
                newNodes = ExpandNodes.expandNodes(currentNode.getState());
                numberExpandedNodes += newNodes.size();

                for (int i = 0; i < newNodes.size(); i++) {


                    if (!isInList(visitedNodes, newNodes.get(i))) {

                        visitedNodes.add(newNodes.get(i));
                        Node childNode = new Node(newNodes.get(i));
                        childNode.setParent(currentNode);
                        childNode.setHCost(0);
                        childNode.setGCost(childNode.getParent().getGCost() + 1);
                        priorityQueue.add(childNode);
                    }


                }

                if (priorityQueue.size() > maxQueueNodes) {
                    maxQueueNodes = priorityQueue.size();

                }

                currentNode = priorityQueue.poll();
                PrintResult.printCurrentState(currentNode.getState(), currentNode.getGCost(), currentNode.getHCost(), boardSize);


            }

            System.out.println("Goal!!!");
            PrintResult.printResult(numberExpandedNodes, maxQueueNodes, getDepth(currentNode));

        }


    }


    public static boolean isSolvable(int[] state) {  //Tests if the puzzle is solvable. The board width must be odd.

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

    public static boolean isInList(List<int[]> list, int[] elem) {  //Method used to see if a list of arrays contains a specific array

        for (final int[] item : list) {
            if (Arrays.equals(item, elem)) {
                return true;
            }
        }
        return false;
    }

    public int getDepth(Node node) {
        int depth = 0;
        while (node.getParent() != null) {
            depth++;
            node = node.getParent();
        }
        ;
        return depth;
    }

    public void setGoalState() {


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
                break;
        }


    }
}
