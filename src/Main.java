/**
 * Created by eldgb on 15-Oct-16.
 */
public class Main {

    public static void main(String[] args) {

        int[] state = {1, 2, 3, 4, 8, 0, 7, 6, 5};
        Node initialState = new Node(state);
        Search search = new Search(initialState);

        search.aStar(Heuristic.MANHATTAN);
        search.aStar(Heuristic.MISPLACEDTILE);

    }


}
