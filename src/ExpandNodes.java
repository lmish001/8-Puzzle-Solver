import java.util.ArrayList;
import java.util.List;

/**
 * Created by eldgb on 15-Oct-16.
 */

public class ExpandNodes {

    public static List<Node> expandNodes(int[] currentState) {  //Calculates all the possible moves form the current state

        List<Node> newNodes = new ArrayList<>(); //Contains all the successor nodes of the node that is passed
        int[] newState;
        Node nextNode;
        int boardSize = (int) Math.sqrt(currentState.length);
        int i = getIndex(currentState, 0);
        int row = Math.floorDiv(i, boardSize); //Row where the blank is
        int column = i % boardSize; //Column where the blank is

        if (column != 0) {  // Move the blank left

            newState = swap(currentState, i, i - 1);
            nextNode = new Node(newState);
            newNodes.add(nextNode);
        }

        if (column != boardSize - 1) { //Move blank to the right

            newState = swap(currentState, i, i + 1);
            nextNode = new Node(newState);
            newNodes.add(nextNode);
        }

        if (row != 0) { //Move blank up

            newState = swap(currentState, i, i - 3);
            nextNode = new Node(newState);
            newNodes.add(nextNode);
        }

        if (row != boardSize - 1) { //Move blank down

            newState = swap(currentState, i, i + 3);
            nextNode = new Node(newState);
            newNodes.add(nextNode);
        }

        return newNodes;
    }

    public static int[] swap(int[] array, int pos1, int pos2) { //Swaps two positions of an array

        int[] tempArray = new int[array.length];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        int temp = tempArray[pos1];
        int temp2 = tempArray[pos2];
        tempArray[pos1] = temp2;
        tempArray[pos2] = temp;
        return tempArray;
    }

    public static int getIndex(int[] array, int number) { //Gets the index of a value of an array

        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {

                return i;
            }
        }

        return 0;
    }
}
