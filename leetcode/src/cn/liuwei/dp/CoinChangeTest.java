package cn.liuwei.dp;

public class CoinChangeTest {

    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * 示例 2：
     * <p>
     * 输入：coins = [2], amount = 3
     * 输出：-1
     * 示例 3：
     * <p>
     * 输入：coins = [1], amount = 0
     * 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 231 - 1
     * 0 <= amount <= 104
     */

    public int coinChange(int[] coins, int amount) {
        // dp[x]代表凑成x所需要的最少硬币个数
        int[] dp = new int[amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
                }
            }
        }

        return dp[amount];
    }

}
