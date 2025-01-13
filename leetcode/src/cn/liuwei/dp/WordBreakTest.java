package cn.liuwei.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class WordBreakTest {

    /**
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * <p>
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     * 示例 2：
     * <p>
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     * 注意，你可以重复使用字典中的单词。
     * 示例 3：
     * <p>
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 300
     * 1 <= wordDict.length <= 1000
     * 1 <= wordDict[i].length <= 20
     * s 和 wordDict[i] 仅由小写英文字母组成
     * wordDict 中的所有字符串 互不相同
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[x]代表字符串s中0-x子串是否可以使用字典内的单词拼接成
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (String dic : wordDict) {
                int start = i - dic.length();
                // 以当前单词结尾且前一段字符串也可以用字典内单词拼接成
                if (start >= 0 && dp[start] && s.substring(start, i).equals(dic)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    @Test
    public void test1() {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        boolean flag = wordBreak("leetcode", wordDict);
        Assertions.assertTrue(flag);
    }

    @Test
    public void test2() {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");
        boolean flag = wordBreak("applepenapple", wordDict);
        Assertions.assertTrue(flag);
    }

    @Test
    public void test3() {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        boolean flag = wordBreak("catsandog", wordDict);
        Assertions.assertFalse(flag);
    }

}
