package cn.liuwei.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BestTimeToBuyAndSellStock4Test {

    /**
     * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     *
     *
     * 示例 1：
     *
     * 输入：k = 2, prices = [2,4,1]
     * 输出：2
     * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
     * 示例 2：
     *
     * 输入：k = 2, prices = [3,2,6,5,0,3]
     * 输出：7
     * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     *
     *
     * 提示：
     *
     * 1 <= k <= 100
     * 1 <= prices.length <= 1000
     * 0 <= prices[i] <= 1000
     */

    public int maxProfit(int k, int[] prices) {
        // 0：未购买股票 1：第一次购买股票 2：第一次出售股票 3：第二次购买股票 4：第二次出售股票，以此类推交易k次，有2k+1种状态
        // dp[x]表示在x状态下售出股票的最大总金额
        int[] dp = new int[2*k + 1];
        for (int i = 1; i < 2*k + 1; i+=2) {
            dp[i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < (1 + 2 * k); j++) {
                int tradeResult = ((j & 1) == 0 ? 1 : -1) * prices[i] + dp[j - 1];
                dp[j] = Math.max(tradeResult, dp[j]);
            }
        }

        return dp[2*k];
    }

    @Test
    public void test1() {
        int maxProfit = maxProfit(2, new int[]{2,4,1});
        Assertions.assertEquals(2, maxProfit);
    }

    @Test
    public void test2() {
        int maxProfit = maxProfit(2, new int[]{3,2,6,5,0,3});
        Assertions.assertEquals(7, maxProfit);
    }

}
