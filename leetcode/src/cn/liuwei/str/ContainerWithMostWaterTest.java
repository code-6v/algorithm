package cn.liuwei.str;

public class ContainerWithMostWaterTest {

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * <p>
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 返回容器可以储存的最大水量。
     * <p>
     * 说明：你不能倾斜容器。
     * <p>
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * 示例 2：
     * 输入：height = [1,1]
     * 输出：1
     * <p>
     * 提示：
     * <p>
     * n == height.length
     * 2 <= n <= 105
     * 0 <= height[i] <= 104
     */

    public int maxArea(int[] height) {
        // 容量最大值
        int max = 0;
        // 左边指针
        int left = 0;
        // 右边指针
        int right = height.length - 1;
        while (left < right) {
            // 当左边比右边高时或者，左边和右边相等，但是右边向左移比左边向右移高度会变高时
            // 右边指针向左移动
            if (height[left] > height[right] || (height[left] == height[right] && left + 1 < right - 1 && height[right - 1] > height[left + 1])) {
                max = Math.max(max, (right - left) * height[right]);
                right--;
            } else {
                // 其余情况，左边指针向右移动
                max = Math.max(max, (right - left) * height[left]);
                left++;
            }
        }
        return max;
    }

}
