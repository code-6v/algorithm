package cn.liuwei.str;

public class LongestCommonPrefixTest {

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     * <p>
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     */

    public String longestCommonPrefix(String[] strs) {
        String str = strs[0];
        if (strs.length == 1) {
            return str;
        }
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < str.length()) {
            boolean flag = false;
            for (int i = 1; i < strs.length; i++) {
                if (index == strs[i].length() || strs[i].charAt(index) != str.charAt(index)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
            sb.append(str.charAt(index));
            index++;
        }
        return sb.toString();
    }

}
