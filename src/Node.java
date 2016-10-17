/**
 * Created by eldgb on 15-Oct-16.
 */
public class Node {

    int [] state;
    int cost;

    public Node (int[] state){
        this.state = state;
    }

    public int[] getState(){
        return state;
    }

    public int getCost(){
        return cost;
    }

    public void setCost (int cost) {
        this.cost = cost;
    }
}
