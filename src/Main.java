/**
 * Created by eldgb on 15-Oct-16.
 */
public class Main {

    public static void main(String[] args) {

        int[] state = {8, 6, 7, 2, 5, 4, 3, 0, 1};
        //{1, 5,3,4,0,6,7,2,8};
//{8, 6, 7, 2 ,5 ,4,3 ,0, 1}; // depth = 31
        //{1,2,3,4,5,6,7,8,9,10,11,12,13,15,14,0}
        Node initialState = new Node(state);
        Search search = new Search(initialState);

        search.aStar(Heuristic.MANHATTAN);
        // search.aStar(Heuristic.MISPLACEDTILE);
        // search.uniformSearch();
    }


}
