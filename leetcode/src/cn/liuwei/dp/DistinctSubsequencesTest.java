package cn.liuwei.dp;

public class DistinctSubsequencesTest {

    /**
     * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 10^9 + 7 取模。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * 解释：
     * 如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
     * rabbbit
     * rabbbit
     * rabbbit
     * 示例 2：
     *
     * 输入：s = "babgbag", t = "bag"
     * 输出：5
     * 解释：
     * 如下所示, 有 5 种可以从 s 中得到 "bag" 的方案。
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     *
     *
     * 提示：
     *
     * 1 <= s.length, t.length <= 1000
     * s 和 t 由英文字母组成
     */

    public int numDistinct(String s, String t) {
        // dp[x][y]表示在 s 的 由前x字符构成的子串 的 子序列 中 t 的 由前y字符构成的子串  出现的个数
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1];
                }
            }
        }

        return dp[s.length()][t.length()] % (1000000000 + 7);
    }

}
