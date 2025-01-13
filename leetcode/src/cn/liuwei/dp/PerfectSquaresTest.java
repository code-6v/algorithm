package cn.liuwei.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PerfectSquaresTest {

    /**
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     *
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * 示例 2：
     *
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     *
     * 提示：
     *
     * 1 <= n <= 10^4
     */

    public int numSquares(int n) {
        int length = 1;
        while ((length * length) < n) {
            length++;
        }
        int[] perfectSquares = new int[length];
        for (int i = 0; i < length; i++) {
            perfectSquares[i] = (i + 1) * (i + 1);
        }
        // dp[x]代表和为x时完全平方数的最少数量
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
        }
        // 外层遍历数值内层遍历目标和，求组合
        // 外层遍历目标和内层遍历数组，求排列
        for (int i = 0; i < length; i++) {
            // j从小到大遍历，数值可以重复使用
            for (int j = perfectSquares[i]; j < n + 1; j++) {
                dp[j] = Math.min(dp[j - perfectSquares[i]] + 1, dp[j]);
            }
        }
        return dp[n];
    }

    @Test
    public void test1() {
        int i = numSquares(12);
        Assertions.assertEquals(3, i);
    }

}
