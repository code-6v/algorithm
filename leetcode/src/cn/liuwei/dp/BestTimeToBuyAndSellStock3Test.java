package cn.liuwei.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BestTimeToBuyAndSellStock3Test {

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     * 示例 2：
     * <p>
     * 输入：prices = [1,2,3,4,5]
     * 输出：4
     * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3：
     * <p>
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
     * 示例 4：
     * <p>
     * 输入：prices = [1]
     * 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 105
     */

    public int maxProfit(int[] prices) {
        // 存在5种状态 0：未购买股票 1：第一次购买股票 2：第一次出售股票 3：第二次购买股票 4：第二次出售股票
        // dp[x]表示在x状态下售出股票的最大总金额
        int[] dp = new int[5];
        dp[1] = -prices[0];
        dp[3] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < 5; j++) {
                int tradeResult = ((j & 1) == 0 ? 1 : -1) * prices[i] + dp[j - 1];
                dp[j] = Math.max(tradeResult, dp[j]);
            }
        }

        return dp[4];
    }

    @Test
    public void test1() {
        int maxProfit = maxProfit(new int[]{1, 4, 2});
        Assertions.assertEquals(3, maxProfit);
    }

    @Test
    public void test2() {
        int maxProfit = maxProfit(new int[]{3,3,5,0,0,3,1,4});
        Assertions.assertEquals(6, maxProfit);
    }

    @Test
    public void test3() {
        int maxProfit = maxProfit(new int[]{1,2,3,4,5});
        Assertions.assertEquals(4, maxProfit);
    }

}
