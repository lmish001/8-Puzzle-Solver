import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eldgb on 15-Oct-16.
 */
public class ExpandNodes {

    public static List <int[]> expandNodes(int[] currentState){

        List <int[]> newNodes = new ArrayList<int[]>();
        int[] newState = new int[currentState.length];


       int i = getIndex(currentState, 0);
        
        System.out.println(i);
       if (i!=0 && i!=3 && i!= 6){  // Move the blank left
        	System.out.println("Current "+Arrays.toString(currentState));
            newState = swap (currentState, i, i-1);
            System.out.println("Current "+Arrays.toString(currentState));
            newNodes.add(newState);
            System.out.println("Left "+Arrays.toString(newState));
        }

        if (i!=2 && i!=5 && i!=8){ //Move blank to the right

            newState = swap (currentState, i, i+1);
            newNodes.add(newState);
           System.out.println("Right "+Arrays.toString(newState));
        }

        if (2<i && i<9){ //Move blank up
            newState = swap (currentState, i, i-3);
            newNodes.add(newState);
            System.out.println("Up "+Arrays.toString(newState));
        }

        if (i<6){ //Move blank down
            newState = swap (currentState, i, i+3);
            newNodes.add(newState);
            System.out.println("Down "+Arrays.toString(newState));
        }

        return newNodes;

    }

    public static int[] swap(int[] array, int pos1, int pos2){
        int [] tempArray = new int [array.length];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        int temp = tempArray[pos1];
        int temp2 = tempArray[pos2];
        tempArray[pos1] = temp2;
        tempArray[pos2] = temp;

        return tempArray;
    }
    
    public static int getIndex (int[] array, int number){
    	for (int i=0; i<array.length; i++){
    		if (array[i] == number){
    			return i;
    		}
    		
    	}
    	return 0;
    }
    }

