package cn.liuwei.stacksandqueues;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 前 K 个高频元素
 */
public class TopKFrequentElements {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     *
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 105
     * k 的取值范围是 [1, 数组中不相同的元素的个数]
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
     *  
     *
     * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/top-k-frequent-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 使用map统计nums数组中各元素出现的次数，然后根据map中val使用快排从大到小排序，最后取出排前k的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequentElements1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer[]> list = new ArrayList<>();
        map.forEach((key, val) -> {
            list.add(new Integer[]{key, val});
        });

        list.sort((item1, item2) -> item2[1] - item1[1]);

        int[] ints = new int[k];
        for (int i = 0; i < k; i++) {
            ints[i] = list.get(i)[0];
        }
        return ints;
    }

    /**
     * 使用map统计nums数组中各元素出现的次数，然后使用优先队列排序排序，最后取出排前k的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequentElements(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(item -> item[1]));
        map.forEach((key, val) -> {
            if (priorityQueue.size() < k) {
                priorityQueue.add(new Integer[]{key, val});
            } else if (val > priorityQueue.peek()[1]){
                priorityQueue.poll();
                priorityQueue.add(new Integer[]{key, val});
            }
        });

        int[] ints = new int[k];
        int i = 0;
        for (Integer[] integers : priorityQueue) {
            ints[i] = integers[0];
            i++;
        }
        return ints;
    }

    @Test
    public void test1() {
        // 输入: nums = [1,1,1,2,2,3], k = 2
        // 输出: [1,2]
        int[] ints = topKFrequentElements(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Assertions.assertArrayEquals(new int[]{1, 2}, ints);
    }

    @Test
    public void test2() {
        // 输入: nums = [1], k = 1
        // 输出: [1]
        int[] ints = topKFrequentElements(new int[]{1}, 1);
        Assertions.assertArrayEquals(new int[]{1}, ints);
    }
}
