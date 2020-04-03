import java.util.*;

class QuestionSolutions {
    /**
     * [Best Time to Buy and Sell I]
     *  Suppose you have an array of prices of a stock for each day for
     *  some period of time. You can only make 1 trade, and you must buy
     *  the stock before you can sell it.
     *
     *  Find the maximum profit possible for the stock given the stock prices.
     *
     *  Leetcode Reference: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     *
     *  Examples
     *      Input: [ 3, 1, 5, 10, 2 ]
     *      Output: 9
     *      Explanation: Buy on day 1 @ $1, sell on day 3 @ $10
     *
     *      Input: [ 5, 4, 3, 2 ]
     *      Output: 0
     *      Explanation: There's no buy-sell pair that yields profit
     *
     *      Input: [ 1, 2, 3, 5, 7 ]
     *      Output: 6
     *      Explanation: Buy on day 0 @ $1, sell on day 4 @ $7
     *
     * @param prices - list of stock prices for each day
     * @return the maximum profit possible given the stock prices
     */
    static int maxProfit(int[] prices) {
        int curLow = Integer.MAX_VALUE;
        int bestProfit = 0;
        for (int price : prices) {
            if (price < curLow) {
                curLow = price;
            } else if (price - curLow > bestProfit) {
                bestProfit = price - curLow;
            }
        }
        return bestProfit;
    }

    /**
     * [Count Ocean Views]
     * Given a list of building heights, suppose there is an ocean
     * view at the end of the array. A building has an ocean view if
     * its height is strictly greater than all buildings between it
     * and the ocean. For example, the array [ 5, 1, 4, 2, 3, 1 ] would
     * look like:
     *
     *      x
     *      x       x
     *      x       x       x
     *      x       x   x   x
     *      x   x   x   x   x   x  ----(ocean)----
     *    i=0   1   2   3   4   5
     *
     * and there would be 4 buildings that can see the ocean (i={0, 2, 4, 5})
     *
     * Return the number of buildings with an ocean view.
     * @param buildings - list of building heights from left to right
     * @return - the number of buildings with ocean views
     */
    static int oceanView(int[] buildings) {
        int curMax = 0;
        int numViews = 0;
        for (int i = buildings.length-1; i >= 0; i--) {
            if (buildings[i] > curMax) {
                numViews++;
                curMax = buildings[i];
            }
        }
        return numViews;
    }

    /**
     * [Merge Intervals]
     * Given a list of intervals in the form [startTime, endTime] that may or may
     * not be overlapping, merge any overlapping intervals. For example, two intervals
     * [1, 5] and [3, 7] can be merged together into [1, 7].
     *
     * Leetcode Reference: https://leetcode.com/problems/merge-intervals/
     *
     * Examples
     *  Input: [ [8,10], [2,6], [1,3], [15,18] ]
     *  Output: [ [1,6], [8,10], [15,18] ]
     *  Why: Since intervals [1,3] and [2,6] overlap, merge them into [1,6]
     *
     *  Input: [ [1,4], [4,5] ]
     *  Output: [ [1, 5] ]
     *  Why: The two intervals are considered overlapping if the end time
     *       is equal to the start time
     *
     * @param intervals - array of interval (start, end) pairs
     * @return - the same array of intervals with any overlapping intervals merged into one
     */
    static int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length == 0) return intervals;

        // Sort the intervals by START time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Accumulate the final merged intervals
        ArrayList<int[]> merged = new ArrayList<int[]>();
        int[] curMerge = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (curMerge[1] >= next[0]) {
                // next interval can be merged
                curMerge[1] = Math.max(next[1], curMerge[1]);
            } else {
                // next interval cannot be merged
                merged.add(curMerge);
                curMerge = next;
            }
        }
        merged.add(curMerge);
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * [Product Excluding Self]
     * Given a list of numbers, return an array where output[i] is the
     * product of all the numbers in `nums` except nums[i]. That is:
     *      output[i] = nums[0] * nums[1] * ... * nums[i-1] * nums[i+1] * ... * nums[nums.length-1]
     *
     * Leetcode Reference: https://leetcode.com/problems/product-of-array-except-self/
     *
     *  Main Challenge: Can you complete this without using division?
     *  Challenge #1: Can you solve this in linear time?
     *  Challenge #2: Can you solve this using constant memory (excluding returned array)?
     *
     *  Examples
     *      Input: [ 1, 2, 3, 4 ]
     *      Output: [ 24, 12, 8, 6 ]
     *
     * @param nums - list of numbers
     * @return the output array described above
     */
    static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] prods = new int[nums.length];

        // LEFT: prods[i] = nums[0] * nums[1] * ... * nums[i-1]
        int lProd = 1;
        prods[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            lProd *= nums[i - 1];
            prods[i] = lProd;
        }

        // RIGHT: prods[i] = nums[len-1] * ... * nums[i+1]
        int rProd = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rProd *= nums[i+1];
            prods[i] *= rProd;
        }

        return prods;
    }


    /**
     * [k-Nearest Restaurants]
     *  Given a list of xy-coordinates of restaurants and a coordinate for a home, return
     *  the nearest k restaurants to the home.
     *
     *  Solution 1 Runtime: O(nk), which would be better for smaller values of k
     *  Solution 2 Runtime: O(n log n), which would be better for larger values of k
     *
     * @param restaurants is a list of xy-coordinates for the restaurants
     * @param home is the xy-coordinate of the home
     * @param k is the number of restaurants to return
     * @return the k-nearest restaurants to the home
     */
    static Coordinate[] kNearestRestaurants(Coordinate[] restaurants, Coordinate home, int k) {
        // Convert into CoordinateDist objects to include distances to reference point `home`
        // Could easily be replaced with a Map<Coordinate, Double> instead
        CoordinateDist[] restaurantDists = new CoordinateDist[restaurants.length];
        Coordinate[] nearestK = new Coordinate[Math.min(k, restaurants.length)];
        for (int i = 0; i < restaurants.length; i++) {
            restaurantDists[i] = new CoordinateDist(restaurants[i], home);
        }

        // If k is too big, just sort and return the distances (no filtering needed)
        if (k >= restaurantDists.length) {
            Arrays.sort(restaurantDists);
            for (int i = 0; i < restaurantDists.length; i++)
                nearestK[i] = restaurantDists[i].coord;
            return nearestK;
        }

        // ==== BEGIN SOLUTION 1 ====
        Set<CoordinateDist> added = new HashSet<CoordinateDist>();
        for (int i = 0; i < k; i++) {
            CoordinateDist champion = restaurantDists[0];
            for (CoordinateDist restaurantDist : restaurantDists) {
                if (!added.contains(restaurantDist) && restaurantDist.distance < champion.distance)
                    champion = restaurantDist;
            }
            nearestK[i] = champion.coord;
            added.add(champion);
        }
        // ==== END SOLUTION 1 ====

        // ==== BEGIN SOLUTION 2 ====
        // Sort by ascending distances
        Arrays.sort(restaurantDists,
                (cDist1, cDist2) ->
                        cDist1.distance - cDist2.distance > 0 ? 1 : cDist1.distance == cDist2.distance ? 0 : -1);

        // Find the first k elements
        for (int i = 0; i < k; i++) {
            nearestK[i] = restaurantDists[i].coord;
        }
        // ==== END SOLUTION 2 ====


        return nearestK;
    }

    // Inner class used for kNearestRestaurants problem
    // Represents an (X, Y) coordinate
    static class Coordinate {
        private final int x;  // x coordinate
        private final int y;  // y coordinate

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    // Private helper method for kNearestRestaurants problem
    // Return the Euclidean distance between two points [(x1-x2)^2 + (y1-y2)^2]^0.5
    private static double euclideanDistance(Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2));
    }

    // Inner class used for kNearestRestaurants problem
    // Represents an (X, Y) coordinate and its distance to a query point
    private static class CoordinateDist {
        private final Coordinate coord;  // Coordinate this represents
        private final double distance;   // Distance between coord and reference point

        CoordinateDist(Coordinate coord, Coordinate reference) {
            this.coord = coord;
            this.distance = euclideanDistance(coord, reference);
        }
    }


    /**
     * [k-Most Frequent Words]
     *  Given a (potentially very large) corpus string of space-separated words and a list of
     *  skip words to omit, return the k-most frequently occurring words that do not appear
     *  in the list of omitted words. Ignore casing and break ties arbitrarily.
     *
     * @param corpus is a space-separated string of words to consider
     * @param skipWords is a list of words to omit from your counts
     * @param k is the number of words to return
     * @return the k-most frequently occurring words in the corpus
     */
    static String[] kMostFrequentWords(String corpus, String[] skipWords, int k) {
        // Pre-process the list of skipWords into a set for quick contains operations
        Set<String> omitted = new HashSet<String>();
        Collections.addAll(omitted, skipWords);

        // Count up all the words in the corpus into a Map of word -> frequency
        String[] words = corpus.split(" ");
        Map<String, Integer> wordCounts = new HashMap<String, Integer>();
        for (String word : words) {
            word = word.toLowerCase();
            if (!omitted.contains(word))
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        // Search for the top-k most frequent words
        String[] topK = new String[Math.min(wordCounts.keySet().size(), k)];
        Set<String> added = new HashSet<String>();
        for (int i = 0; i < Math.min(wordCounts.keySet().size(), k); i++) {
            String champion = null;
            int maxFreq = Integer.MIN_VALUE;

            // Find the most frequent unused word and add it to the list
            for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
                if (!added.contains(wordCount.getKey()) && wordCount.getValue() > maxFreq) {
                    champion = wordCount.getKey();
                    maxFreq = wordCount.getValue();
                }
            }
            topK[i] = champion;
            added.add(champion);
        }
        return topK;
    }

    /**
     * [Number of Islands]
     * Given a grid of 0s and 1s, return the number of unique islands there are (1 being
     * land and 0 being ocean). Assume the edges are ocean. Connections are only between
     * up, down, left, and right (no diagonals).
     *
     * Input: [[ 1, 1, 0, 0, 1 ],
     *         [ 1, 1, 0, 1, 1 ],
     *         [ 0, 0, 1, 0, 0 ],
     *         [ 0, 0, 1, 1, 1 ]]
     * Output: 3
     *
     * @param map contains the list of 0s and 1s
     * @return the number of distinct islands in the map
     */
    static int numIslands(int[][] map) {
        if (map == null || map.length == 0) return 0;

        boolean[][] visited = new boolean[map.length][map[0].length];
        int numIslands = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (!visited[i][j]) {
                    if (map[i][j] == 1) numIslands++;
                    adventure(map, i, j, visited);
                }
            }
        }
        return numIslands;
    }

    // Standard DFS, visit all adjacent nodes until reaching water
    private static void adventure(int[][] map, int row, int col, boolean[][] visited) {
        if (visited[row][col]) return;

        visited[row][col] = true;
        if (map[row][col] == 0) return; // nothing to see here
        // found an island! Let's exploreeeee
        final int[] DIRECTIONS = {0, 1, 2, 3}; // UP, RIGHT, DOWN, LEFT
        for (int direction : DIRECTIONS) {
            int[] rowCol = getRowColFromDirection(row, col, direction);
            int nextRow = rowCol[0];
            int nextCol = rowCol[1];
            if (isValidRowCol(map, nextRow, nextCol))
                adventure(map, nextRow, nextCol, visited);
        }
    }

    // True if (row, col) is within map
    private static boolean isValidRowCol(int[][] map, int row, int col) {
        return row > -1 && col > -1 && row < map.length && col < map[0].length;
    }

    // Return neighbor [row, col] in a given direction
    // PRE: direction \in {0, 1, 2, 3}
    private static int[] getRowColFromDirection(int row, int col, int direction) {
        switch (direction) {
            case 0:
                return new int[]{row, col+1};
            case 1:
                return new int[]{row+1, col};
            case 2:
                return new int[]{row, col-1};
            case 3:
                return new int[]{row-1, col};
        }
        return null;
    }
}
