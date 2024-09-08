package cn.liuwei.greedy;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 1005. K 次取反后最大化的数组和
 */
public class MaximizeSumOfArrayAfterKNegationsTest {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     * <p>
     * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     * <p>
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [4,2,3], k = 1
     * 输出：5
     * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
     * 示例 2：
     * <p>
     * 输入：nums = [3,-1,0,2], k = 3
     * 输出：6
     * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
     * 示例 3：
     * <p>
     * 输入：nums = [2,-3,-1,5,-4], k = 2
     * 输出：13
     * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 104
     * -100 <= nums[i] <= 100
     * 1 <= k <= 104
     */

    public int maxSumAfterKNegations(int[] nums, int k) {
        int sum = 0;
        // 按绝对值排序 倒序
        sort(nums);
        // 将负数尽可能变正数
        // 多余的次数反复操作最小的那个非负数
        for (int i = 0; i < nums.length; i++) {
            if (k > 0 && nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }

            if (i == nums.length - 1 && k % 2 > 0) {
                nums[i] = -nums[i];
            }

            sum += nums[i];
        }

        return sum;
    }

    @Test
    public void test() {
        System.out.println(maxSumAfterKNegations(new int[]{-8, 3, -5, -3, -5, -2}, 6));
    }

    @Test
    public void testSort() {
        int[] nums = new int[20];
        for (int i = 0; i < 20; i++) {
            nums[i] = RandomUtil.randomInt(-100, 100, false, false);
        }
        System.out.println(Arrays.toString(nums));
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int i, int j) {
        if (i >= j) {
            return;
        }
        // 选择一个基点，将小于基点的数放到基点左边，将大于基点的数放到基点的右边
        int point = partition(nums, i, j);
        // 递归处理基点左边的数
        sort(nums, i, point - 1);
        // 递归处理基点右边的数
        sort(nums, point + 1, j);
    }

    private int partition(int[] nums, int i, int j) {
        // 选择一个基点
        int val = nums[i];
        int left = i;
        int right = j;
        while (left < right) {
            // 右指针向左移动，直至遇到大于基点的数，左指针向右移动，直至遇到小于基点的数，左右节点交换(注意右节点要先移动，这样可以保证左右节点相遇时位置的数是大于基点的数)
            while (left < right && compare(nums[right], val) == -1) {
                right--;
            }

            while (left < right && compare(nums[left], val) != -1) {
                left++;
            }

            if (left >= right) {
                break;
            }

            swap(nums, left, right);
        }
        // 左右节点相遇时停止，将停止位置的数与基点交换
        swap(nums, right, i);
        return right;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int compare(int a, int b) {
        int val1 = a >= 0 ? a : -a;
        int val2 = b >= 0 ? b : -b;
        return Integer.compare(val1, val2);
    }

}
