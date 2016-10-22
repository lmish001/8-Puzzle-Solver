/**
 * Created by eldgb on 17-Oct-16.
 */
public class Heuristic {


    public static final String MISPLACEDTILE = "Misplaced tile";
    public static final String MANHATTAN = "Manhattan distance";


    public static int MisplacedTile(int[] state) {

        int totalCost = 0;
        for (int i = 0; i < state.length - 1; i++) {
            if (i + 1 != state[i] && state[i] != 0) {
                totalCost += 1;
            }
        }

        return totalCost;

    }


    public static int ManhattanDistance(int[] state, int boardSize) {

        int totalCost = 0;
        int[][] board = new int[boardSize][boardSize];
        int i, j;
        int k = 0;
        for (i = 0; i < boardSize; i++) {
            for (j = 0; j < boardSize; j++) {
                board[i][j] = state[k];
                k++;
            }
        }

        for (i = 0; i < boardSize; i++) {
            for (j = 0; j < boardSize; j++) {

                int value = board[i][j];
                if (value != 0) {
                    int posx = (value - 1) / boardSize;
                    int posy = (value - 1) % boardSize;
                    int disx = i - posx;
                    int disy = j - posy;
                    totalCost += Math.abs(disx) + Math.abs(disy);

                }


            }
        }

        return totalCost;

    }


}
