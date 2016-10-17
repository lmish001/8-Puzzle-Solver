import java.util.*;
import java.math.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by eldgb on 15-Oct-16.
 */
public class Search {

    Node initialState;
    int [] goalState = {1,2,3,4,5,6,7,8,0};
    int N = (int) Math.sqrt(goalState.length);
    int numberExpandedNodes = 0;
    int maxQueueNodes = 0;
    int depth = 0;

    public Search (Node initialState){

        this.initialState = initialState;
    }

    public boolean aStar (String heuristic){


    //    System.out.print(isSolvable(initialState.getState()));
        boolean solved = false;
        int cost;
        ArrayList <int[]> visitedNodes = new ArrayList <int[]>();
        List <int[]> newNodes = new ArrayList <int[]>();
        Comparator<Node> queueComparator = new NodeCostComparator();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node> (10, queueComparator);

        Node currentNode = initialState;
    //    System.out.println(Arrays.equals(currentNode.getState(),goalState));
        
        
        
        
        

        while (!Arrays.equals(currentNode.getState(),goalState)){
            depth += 1;
            visitedNodes.add(currentNode.getState());
            newNodes = ExpandNodes.expandNodes(currentNode.getState());
            numberExpandedNodes += newNodes.size();

            for (int i=0; i< newNodes.size(); i++){
            	

            		if(!isInList(visitedNodes, newNodes.get(i))){

            			 visitedNodes.add(newNodes.get(i));
                         Node childNode = new Node (newNodes.get(i));
                        System.out.println("Child node: "+Arrays.toString(childNode.getState()));
                         
                         if (heuristic.equals("Manhattan")){

                             childNode.setCost(ManhattanDistance(childNode.getState()));
                             priorityQueue.add(childNode);

                         }
                         
                         
            			
            		}



            }

            if(priorityQueue.size()>maxQueueNodes){
                System.out.println("Queue size: "+priorityQueue.size());
                maxQueueNodes = priorityQueue.size();
               
            }

            currentNode = priorityQueue.poll();
            
            
          printCurrentState(currentNode.getState(), currentNode.getCost());


        }

        System.out.println("Goal!!!" + currentNode.getState());
        printResult(numberExpandedNodes, maxQueueNodes, depth);
        return solved;

    }

    public int ManhattanDistance (int[] state){

        int totalCost = 0;
        int [] [] board = new int [N][N];
        int i, j;
        int k = 0;
        for (i=0; i<N; i++){
            for (j=0; j<N; j++){
                board [i][j] = state[k];
                k++;
            }
        }

        for (i=0; i<N; i++){
            for (j=0; j<N; j++){

                int value = board [i][j];
                if (value!=0){
                    int posx = (value - 1)/N;
                    int posy = (value-1)%N;
                    int disx = i - posx;
                    int disy = j - posy;
                    totalCost += Math.abs(disx) + Math.abs(disy);

                }


            }
        }
      //  System.out.println("Manhattan. State: "+state+" cost: "+totalCost);
      return totalCost;

    }

    public void printCurrentState(int[] currentState, int cost){

        System.out.println("The best state to expand with a g(n) = 1 and h(n) = " +cost+" is…");

        //currentState.replace("0", " ");

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
               System.out.print(currentState[j+(i*N)]+" ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public void printResult (int nodes, int queue, int depth){

        System.out.println ("To solve this problem the search algorithm expanded a total of "+nodes+" nodes.");
        System.out.println ("The maximum number of nodes in the queue at any one time was "+queue);
        System.out.println ("The depth of the goal node was "+depth);

    }

    public boolean isSolvable (String state){

        int inversion = 0;
         for (int i=0; i<state.length()-1; i++){
             if (Integer.parseInt(state.substring(i,i+1))>Integer.parseInt(state.substring(i+1,i+2))){
                 inversion +=1;
             }
         }
         if (inversion%2!=0) {
             return false;
         }
         return true;
    }

    public static boolean isInList(final List<int[]> list, final int[] candidate){

        for(final int[] item : list){
            if(Arrays.equals(item, candidate)){
                return true;
            }
        }
        return false;
    }
}
