import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
}
