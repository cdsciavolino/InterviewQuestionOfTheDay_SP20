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
}
