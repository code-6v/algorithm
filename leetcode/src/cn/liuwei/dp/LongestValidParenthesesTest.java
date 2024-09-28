package cn.liuwei.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestValidParenthesesTest {

    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号
     * 子串
     * 的长度。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * 示例 2：
     * <p>
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     * 示例 3：
     * <p>
     * 输入：s = ""
     * 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 3 * 104
     * s[i] 为 '(' 或 ')'
     */

    @Test
    public void test() {
        int len = longestValidParentheses("(()");
        Assertions.assertEquals(2, len);
    }

    @Test
    public void test1() {
        int len = longestValidParentheses(")()())");
        Assertions.assertEquals(4, len);
    }

    @Test
    public void test2() {
        int len = longestValidParentheses("");
        Assertions.assertEquals(0, len);
    }

    @Test
    public void test3() {
        int len = longestValidParentheses("()()(()()()");
        Assertions.assertEquals(6, len);
    }

    public int longestValidParentheses(String s) {
        int max = 0;
        // dp[i] 以i位字符结尾的最大连续有效括号子串长度
        int[] dp = new int[s.length()];
        //         for (int i = 1; i < s.length(); i++) {
        //            if (s.charAt(i) == ')') {
        //                // 前一个字符是(，如()()，则长度为2+dp[i-2]
        //                if (s.charAt(i - 1) == '(') {
        //                    dp[i] = 2 + (i - 2 > 0 ? dp[i - 2] : 0);
        //                } else {
        //                    // 如果i - dp[i - 1] - 1是(,例如(()),则长度为2+dp[i-1]
        //                    // 此时还需要考虑前面是否还有有效括号，例如()(())这种情况，所以还需要加上dp[i - dp[i - 1] - 2]
        //                    if (dp[i - 1] > 0 && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
        //                        dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
        //                    }
        //                }
        //            }
        //        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            max = Math.max(max, dp[i]);
        }

        return max;
    }

}


