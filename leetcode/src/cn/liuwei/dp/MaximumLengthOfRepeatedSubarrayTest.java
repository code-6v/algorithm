package cn.liuwei.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaximumLengthOfRepeatedSubarrayTest {

    /**
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1] 。
     * 示例 2：
     *
     * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
     * 输出：5
     *
     *
     * 提示：
     *
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 100
     */

    public int findLength(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int max = 0;
        for (int i = 0; i < nums1.length; i++) {
            int pre = dp[0];
            for (int j = 0; j < nums2.length; j++) {
                int cur = dp[j + 1];
                if (nums1[i] == nums2[j]) {
                    dp[j + 1] = pre + 1;
                    max = Math.max(max, dp[j + 1]);
                } else {
                    dp[j + 1] = 0;
                }
                pre = cur;
            }
        }
        return max;
    }

    @Test
    public void test1() {
        int length = findLength(new int[]{1,0,0,0,1}, new int[]{1,0,0,1,1});
        Assertions.assertEquals(3, length);
    }
}
