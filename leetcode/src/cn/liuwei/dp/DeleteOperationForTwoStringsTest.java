package cn.liuwei.dp;

public class DeleteOperationForTwoStringsTest {

    /**
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
     * <p>
     * 每步 可以删除任意一个字符串中的一个字符。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: word1 = "sea", word2 = "eat"
     * 输出: 2
     * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
     * 示例  2:
     * <p>
     * 输入：word1 = "leetcode", word2 = "etco"
     * 输出：4
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= word1.length, word2.length <= 500
     * word1 和 word2 只包含小写英文字母
     */

    public int minDistance(String word1, String word2) {
        // dp[x][y]表示由word1前x个字符构成的子串 和  由word2前y个字符构成的子串 相同所需的最小步数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

}
