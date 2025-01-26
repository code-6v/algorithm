package cn.liuwei.dp;

public class LongestPalindromicSubsequenceTest {

    /**
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * <p>
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     * 示例 2：
     * <p>
     * 输入：s = "cbbd"
     * 输出：2
     * 解释：一个可能的最长回文子序列为 "bb" 。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 1000
     * s 仅由小写英文字母组成
     */

    public int longestPalindromeSubseq(String s) {
        // dp[x][y]表示s的[x,y]子串中最长的回文子序列的长度
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j][i] = 2 + (i - j < 2 ? 0 : dp[j + 1][i - 1]);
                } else {
                    dp[j][i] = Math.max(dp[j][i - 1], Math.max(dp[j + 1][i], i - j < 2 ? 0 : dp[j + 1][i - 1]));
                }
            }
        }
        return dp[0][s.length() - 1];
    }

}
