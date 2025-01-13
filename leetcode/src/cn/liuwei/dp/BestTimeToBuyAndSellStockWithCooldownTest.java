package cn.liuwei.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BestTimeToBuyAndSellStockWithCooldownTest {

    /**
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
     * <p>
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * <p>
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: prices = [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * 示例 2:
     * <p>
     * 输入: prices = [1]
     * 输出: 0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= prices.length <= 5000
     * 0 <= prices[i] <= 1000
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        // 0：购买股票 1：出售股票
        // dp[x]表示在x状态下所剩最大金额
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int tradeResult = -1 * prices[i] + dp[i - 1][1];
            dp[i][0] = Math.max(tradeResult, dp[i - 1][0]);

            tradeResult = prices[i] + dp[i - 1][0];
            dp[i][1] = Math.max(tradeResult, dp[i-1][1]);
        }

        return dp[prices.length - 1][1];
    }

    @Test
    public void test1() {
        int maxProfit = maxProfit(new int[]{1, 2, 3, 0, 2});
        Assertions.assertEquals(3, maxProfit);
    }

    @Test
    public void test2() {
        int maxProfit = maxProfit(new int[]{1});
        Assertions.assertEquals(0, maxProfit);
    }
}
