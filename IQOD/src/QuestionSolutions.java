class QuestionSolutions {
    /**
     * [Best Time to Buy and Sell I]
     *  Suppose you have an array of prices of a stock for each day for
     *  some period of time. You can only make 1 trade, and you must buy
     *  the stock before you can sell it.
     *
     *  Find the maximum profit possible for the stock given the stock prices.
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
}
