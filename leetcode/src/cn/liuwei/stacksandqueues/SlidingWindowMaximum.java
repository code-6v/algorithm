package cn.liuwei.stacksandqueues;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 */
public class SlidingWindowMaximum {

    /*
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回 滑动窗口中的最大值 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * 示例 2：
     *
     * 输入：nums = [1], k = 1
     * 输出：[1]
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/sliding-window-maximum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /*
     * 思路1:
     * 遍历数据并使用优先队列存储遍历过的数,并从第k+1个数开始删除离开窗口的数,从第k个数开始记录最大值
     * 如何从优先队列中删除离开窗口的数? 遍历比较判断
     *
     * 思路2:
     * 尝试使用队列记录窗口中的最大值(队列头放窗口内最大值)
     * 
     * 1.从第k+1个数开始删除离开窗口的数
     * 将离开窗口的元素与window队头比较
     * 相同,则window队头出队
     * 不相同,则不处理
     *
     * 2.遍历nums,记录窗口内的值
     * 定义队列window,记录窗口内元素,且队头为窗口内的最大值
     * 遍历nums,将遍历值从window队头开始比较与队列值比较,直到大于队列的值或者到达队尾,然后将遍历值放到该位置并清空后面的元素
     *
     * 3.从第k个数开始记录最大值
     * 取window队列头
     *
     */

    /**
     * 思路1
     *
     * 测试结果:超时
     * 分析:用最大/小堆构建的优先队列删除元素时需要遍历堆,且删除元素后数组需要进行调整
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] slidingWindowMaximum1(int[] nums, int k) {
        int[] maxArray = new int[nums.length - k + 1];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            if (i >= k){
                priorityQueue.remove(nums[i - k]);
            }
            priorityQueue.add(nums[i]);
            if (i >= k-1){
                maxArray[i - k + 1] = priorityQueue.peek();
            }
        }
        return maxArray;
    }


    static class Node{
        public Integer data;
        public Node next;

        public Node prev;

        public Node(Integer data) {
            this.data = data;
        }
    }

    /**
     * 思路2
     *
     * 测试结果:成功
     *
     * 分析:1.维护从大到小的单调队列,添加元素时,有个特点:新来的比你还大,那你是不是可以out了
     *     2.根据队列特点添加元素时要从后往前比较
     * 
     * @param nums
     * @param k
     * @return
     */
    public static int[] slidingWindowMaximum(int[] nums, int k) {
        int[] maxArray = new int[nums.length - k + 1];
        Node head = null;
        Node tail = null;
        for (int i = 0; i < nums.length; i++) {
            // 删除离开窗口的元素
            if (i >= k){
                if (head.data == nums[i - k]) {
                    if (head.next != null) {
                        head.next.prev = null;
                        head = head.next;
                    }else {
                        head = null;
                        tail = null;
                    }
                }
            }

            // 记录遍历值
            // 注意:需要从后往前遍历,避免window出现如10,9,8...,5添加4时需要从头遍历到尾的情况
            // 分析如果经常出现遍历数大于window里所有数的情况,那么window会被经常清空,所以出现这种情况性能也不会差
            Node current = tail;
            while (current != null && nums[i] > current.data){
                current = current.prev;
            }
            tail = new Node(nums[i]);
            if (current != null) {
                tail.prev = current;
                current.next = tail;
            }else {
                head = tail;
            }


            // 记录窗口内最大值
            if (i >= k-1){
                maxArray[i - k + 1] = head.data;
            }
        }
        return maxArray;
    }

    @Test
    public void test1(){
        //输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        //输出：[3,3,5,5,6,7]
        int[] maximum = slidingWindowMaximum(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        Assertions.assertArrayEquals(new int[]{3,3,5,5,6,7},maximum);
    }

    @Test
    public void test2(){
        //输入：nums = [1], k = 1
        //输出：[1]
        int[] maximum = slidingWindowMaximum(new int[]{1}, 1);
        Assertions.assertArrayEquals(new int[]{1},maximum);
    }

    @Test
    public void test3(){
        //nums = [1,-1] k = 1
        //[1,-1]
        int[] maximum = slidingWindowMaximum(new int[]{1,-1}, 1);
        Assertions.assertArrayEquals(new int[]{1,-1},maximum);
    }
}
