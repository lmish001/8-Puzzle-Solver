import java.util.ArrayList;
import java.util.List;

/**
 * Created by eldgb on 15-Oct-16.
 */
public class ExpandNodes {

    public static List<int[]> expandNodes(int[] currentState) {

        List<int[]> newNodes = new ArrayList<>();
        int[] newState;
        int boardSize = (int) Math.sqrt(currentState.length);


        int i = getIndex(currentState, 0);


        if (i % boardSize != 0) {  // Move the blank left
            newState = swap(currentState, i, i - 1);
            newNodes.add(newState);
        }

        if (i % boardSize != boardSize - 1) { //Move blank to the right

            newState = swap(currentState, i, i + 1);
            newNodes.add(newState);
        }

        if (Math.floorDiv(i, boardSize) != 0) { //Move blank up
            newState = swap(currentState, i, i - 3);
            newNodes.add(newState);
        }

        if (Math.floorDiv(i, boardSize) != boardSize - 1) { //Move blank down
            newState = swap(currentState, i, i + 3);
            newNodes.add(newState);
        }

        return newNodes;

    }

    public static int[] swap(int[] array, int pos1, int pos2) {
        int[] tempArray = new int[array.length];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        int temp = tempArray[pos1];
        int temp2 = tempArray[pos2];
        tempArray[pos1] = temp2;
        tempArray[pos2] = temp;

        return tempArray;
    }

    public static int getIndex(int[] array, int number) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                return i;
            }

        }
        return 0;
    }
}

