package cn.liuwei.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonotoneIncreasingDigitsTest {

    /**
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     * <p>
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     * <p>
     * 示例 1:
     * <p>
     * 输入: N = 10
     * 输出: 9
     * 示例 2:
     * <p>
     * 输入: N = 1234
     * 输出: 1234
     * 示例 3:
     * <p>
     * 输入: N = 332
     * 输出: 299
     * 说明: N 是在 [0, 10^9] 范围内的一个整数。
     */

    // 从大到小遍历，找到满足条件的数
    // 4321     3999
    // 1254
    // 4121     3999
    // 1213     1199
    // 1203     1100
    // 1011     999
    // 1100     999
    // 从最高位开始遍历，如果后一位无法大于等于前一位值（每位的值只能小于等于原始值，如果原始值都还是小于前一位，则无法大于等于），则前一位减一（需要回溯判断前一位是否还是大于等于前前一位），当前以及后面位数的值都设为九
    public int monotoneIncreasingDigits(int n) {
        String num = String.valueOf(n);

        // 记录不满足递增的最高位数
        Integer index = null;
        for (int i = num.length() - 2; i >= 0; i--) {
            if (num.charAt(i) - num.charAt(i + 1) > (index != null && index == i + 1 ? -1 : 0)) {
                index = i;
            }
        }

        // 为null表示都满足递增条件
        if (index == null) {
            return n;
        }

        // 通过整除减一的方式计算
        int val = Double.valueOf(Math.pow(10, num.length() - 1 - index)).intValue();
        return n / val * val - 1;
    }

    @Test
    public void test1() {
        int num = monotoneIncreasingDigits(1234);
        Assertions.assertEquals(1234, num);
    }

    @Test
    public void test2() {
        int num = monotoneIncreasingDigits(332);
        Assertions.assertEquals(299, num);
    }

    @Test
    public void test3() {
        int num = monotoneIncreasingDigits(10);
        Assertions.assertEquals(9, num);
    }

    @Test
    public void test4() {
        int num = monotoneIncreasingDigits(4321);
        Assertions.assertEquals(3999, num);
    }

    @Test
    public void test5() {
        int num = monotoneIncreasingDigits(1212);
        Assertions.assertEquals(1199, num);
    }
}
