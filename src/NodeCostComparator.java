import java.util.Comparator;

/**
 * Created by eldgb on 16-Oct-16.
 */
class NodeCostComparator implements Comparator<Node> {

    @Override
    public int compare(Node cost1, Node cost2) {
        if (cost1.getCost() > cost2.getCost()) {
            return 1;
        } else if (cost1.getCost() < cost2.getCost()) {
            return -1;
        }

        return 0;
    }


}
