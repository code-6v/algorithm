package cn.liuwei.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabelsTest {

    /**
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
     * <p>
     * 示例：
     * <p>
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8] 解释： 划分结果为 "ababcbaca", "defegde", "hijhklij"。 每个字母最多出现在一个片段中。 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     * 提示：
     * <p>
     * S的长度在[1, 500]之间。
     * S只包含小写字母 'a' 到 'z' 。
     */
    @Test
    public void test1() {
        int[] ints = partitionLabels("ababcbacadefegdehijhklij");
        Assertions.assertArrayEquals(new int[]{9, 7, 8}, ints);
    }

    public int[] partitionLabels(String s) {
        // 统计字符串中出现的每个字符的截止位置
        int[] cutoff = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cutoff[c - 'a'] = i;
        }

        // 遍历数组
        // 判断当前遍历过的子串中的字符是否不会再出现，是则截断并记录，否则继续遍历
        List<Integer> list = new ArrayList<>();
        Integer maxCutoff = null;
        int subStrStart = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当前字符截止位置
            int curCutoff = cutoff[c - 'a'];
            if (curCutoff > i && (maxCutoff == null || curCutoff > maxCutoff)) {
                // 更新子串最大截止位置
                maxCutoff = curCutoff;
            } else if (curCutoff == i && (maxCutoff == null || i >= maxCutoff)) {
                // 子串内的字符都不会再出现
                list.add(i - subStrStart + 1);
                subStrStart = i + 1;
                maxCutoff = null;
            }
        }
        
        return list.stream().mapToInt(i->i).toArray();
    }

}
