package cn.liuwei.dp;

public class HouseRobber2Test {

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2：
     *
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 3：
     *
     * 输入：nums = [1,2,3]
     * 输出：3
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(robPart(nums, 0, nums.length - 2), robPart(nums, 1, nums.length - 1));
    }

    private int robPart(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        // dp[x]表示偷窃包含x+1号房屋，可以偷窃到的最高金额
        int length = end - start + 1;
        int[] dp = new int[length];
        dp[0] = nums[start];
        dp[1] = nums[start + 1];
        if (length > 2) {
            dp[2] = nums[start + 2] + nums[start];
        }
        for (int i = start + 3; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i];
        }

        return Math.max(dp[end], dp[end - 1]);
    }


}
