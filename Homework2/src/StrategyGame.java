import java.util.ArrayList;

/**
 * Created by Henrik on 12/2/2015.
 * @author Henrik Drefs, 70428166
 */
public class StrategyGame {

    private int[][] field;
    private int rows;
    private int columns;

    /**
     * constructor for StrategyGame object, sets field
     * @param field game field
     */
    public StrategyGame(int[][] field) {
        this.field = field;
        this.rows = field.length;
        this.columns = field[0].length;
    }

    /**
     * calculates the safest route through the field using dynamic programming
     * @return int array containing the column indexes for each row in the safest route
     */
    public int[] safestRoute() {
        // Cache for the next index to go for safest route, not needed for last row
        int[][] pathCache = new int[rows - 1][columns];
        // Cache for danger value of safest from each element
        int[][] dangerCache = new int[rows][columns];
        // last row danger values are put in cache
        dangerCache[rows - 1] = field[rows - 1];

        //iterate through all cells starting with the second last row
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j < columns; j++) {
                // determine the dangers of paths going down-left, down-straight and down-right
                int left, straight, right;
                if (j > 0) {
                    left = dangerCache[i + 1][j - 1];
                } else {
                    left = Integer.MAX_VALUE;   // because left cell does not exist and
                                                // with max value, it can never be the minimum
                }
                straight = dangerCache[i + 1][j];
                if (j + 1 < columns) {
                    right = dangerCache[i + 1][j + 1];
                } else {
                    right = Integer.MAX_VALUE;  // see above
                }
                if (left < straight) {
                    if (left < right) { // left < right && left < straight
                        // safest route from here goes down-left
                        pathCache[i][j] = j - 1;
                        // danger of safest path starting here
                        dangerCache[i][j] = dangerCache[i + 1][j - 1] + field[i][j];
                    } else { // right < left < straight
                        // safest route from here goes down right
                        pathCache[i][j] = j + 1;
                        // danger of safest path starting here
                        dangerCache[i][j] = dangerCache[i + 1][j + 1] + field[i][j];
                    }
                } else {
                    if (straight < right) { // straight < left && straight < right
                        // safest route from here goes down straight
                        pathCache[i][j] = j;
                        // danger of safest path starting here
                        dangerCache[i][j] = dangerCache[i + 1][j] + field[i][j];
                    } else { // left > straight > right
                        // safest route from here goes down right
                        pathCache[i][j] = j + 1;
                        // danger of safest path starting here
                        dangerCache[i][j] = dangerCache[i + 1][j + 1] + field[i][j];
                    }
                }
            }
        }
        // get safest from cache
        int[] res = new int[rows];
        res[0] = 0;
        //determine safest starting point
        for (int i = 1; i < columns; i++) {
            if (dangerCache[0][i] < dangerCache[0][res[0]]) {
                res[0] = i;
            }
        }
        //get all indexes from path cache for safest route
        for (int i = 1; i < rows; i++) {
            res[i] = pathCache[i - 1][res[i - 1]];
        }
        return res;
    }

    public ArrayList<Integer> safestRouteRecursive() {
        ArrayList<Integer> route = new ArrayList<Integer>();
        for (int i = 0; i < columns; i++) {
            ArrayList<Integer> cur = safestRouteRec(0, i);
            if (danger(cur) < danger(route)) {
                route = cur;
            }
        }
        return route;
    }

    private ArrayList<Integer> safestRouteRec(int y, int x) {
        ArrayList<Integer> route = new ArrayList<Integer>();
        route.add(x);
        if (y == rows - 1) {
            return route;
        }
        ArrayList<Integer> left = x > 0 ? safestRouteRec(y + 1, x - 1) : null;
        ArrayList<Integer> straight = safestRouteRec(y + 1, x);
        ArrayList<Integer> right = x + 1 < columns ? safestRouteRec(y + 1, x + 1) : null;
        if (danger(left) < danger(straight)) {
            if (danger(left) < danger(right)) {
                route.addAll(left);
            } else {
                route.addAll(right);
            }
        } else {
            if (danger(straight) < danger(right)) {
                route.addAll(straight);
            } else {
                route.addAll(right);
            }
        }
        return route;
    }

    public int danger(ArrayList<Integer> in) {
        if (in == null || in.size() == 0) {
            return Integer.MAX_VALUE;
        }
        int danger = 0;
        int lastIndex = in.size() - 1;
        int j = 0;
        for (int i = lastIndex; i >= 0; i--) {
            int index = in.get(lastIndex - j);
            danger += field[rows - j - 1][index];
            j++;
        }
        return danger;
    }

    public static void main(String[] args) {
        // Example from task sheet
        StrategyGame game = new StrategyGame(new int[][]{{6, 2, 4, 1}, {1, 2, 5, 4},
                {2, 7, 3, 2}, {1, 2, 2, 5}, {9, 5, 1, 6}});
//        System.out.println(Arrays.toString(game.safestRoute()));
        // prints [1, 0, 0, 1, 2]
        // which represents the safest route as shown on task sheet
        System.out.println(game.safestRouteRecursive());
    }
}
