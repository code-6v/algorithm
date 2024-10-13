package cn.liuwei.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TargetSumTest {

    /**
     * 给你一个非负整数数组 nums 和一个整数 target 。
     * <p>
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * <p>
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * 示例 2：
     * <p>
     * 输入：nums = [1], target = 1
     * 输出：1
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 1000
     */
    @Test
    public void test1() {
        int count = targetSum(new int[]{1, 1, 1, 1, 1}, 3);
        Assertions.assertEquals(5, count);
    }

    @Test
    public void test2() {
        int count = targetSum(new int[]{1}, 1);
        Assertions.assertEquals(1, count);
    }

    public int targetSum(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 有多少种方法使运算结果等于负的target值就会有相同多种方法使运算结果等于正的target值
        target = target < 0 ? -target : target;

        // 不能整除代表没办法运算成target
        if ((sum + target) % 2 == 1) {
            return 0;
        }

        int weight = (sum + target) / 2;

        // 如果数组内所有的数相加都达不到，则表示没办法运算成target
        if (weight > sum) {
            return 0;
        }

        // dp[j]代表能背重量j的背包（放置数的总和不大于j），有多少种方法放置数组中的数
        int[] dp = new int[weight + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = weight; j >= nums[i]; j--) {
                // 新增一个数时dp[j]等于不放该数的方法数（取上轮的dp值）加上放置该数的方法数（1*减去该值还需要的目标值的dp值）
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }

        return dp[weight];
    }
}
