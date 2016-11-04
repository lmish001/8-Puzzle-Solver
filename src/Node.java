import java.util.Arrays;

/**
 * Created by eldgb on 15-Oct-16.
 */
public class Node {

    private int[] state;  //Position of the tiles
    private int h_cost;
    private int g_cost;
    private Node parentNode;

    public Node(int[] state) {
        this.state = state;
    }

    public int[] getState() {
        return state;
    }

    public int getCost() {
        return h_cost + g_cost;
    }

    public Node getParent() {
        return parentNode;
    }

    public int getGCost() {
        return g_cost;
    }

    public int getHCost() {
        return h_cost;
    }

    public String getAsString() {
        return Arrays.toString(state);
    }

    public void setGCost(int cost) {
        this.g_cost = cost;
    }

    public void setHCost(int cost) {
        this.h_cost = cost;
    }

    public void setParent(Node parentNode) {
        this.parentNode = parentNode;
    }


}
