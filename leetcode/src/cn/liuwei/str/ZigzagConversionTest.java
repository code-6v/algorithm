package cn.liuwei.str;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 6. Z 字形变换
 */
public class ZigzagConversionTest {

    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     * <p>
     * 请你实现这个将字符串进行指定行数变换的函数：
     * <p>
     * string convert(string s, int numRows);
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     * 示例 2：
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * 示例 3：
     * <p>
     * 输入：s = "A", numRows = 1
     * 输出："A"
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 1000
     * s 由英文字母（小写和大写）、',' 和 '.' 组成
     * 1 <= numRows <= 1000
     */

    /**
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     *
     * 第一行PA，在原字符串中相差4 （即A，Y，P，A）
     * 第二行AP，在原字符串中相差2 （即Y，P）
     *
     * 定义curRow代表当前行
     * 推导V型同行相邻的两个字符在原字符串中相差 2*(numRows - curRow)
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     *
     * 第三行YI，在原字符串中相差4 （即P，A，L，I）
     * 第二行PL，在原字符串中相差2 （即A，L）
     *
     * 推导倒V型同行相邻的两个字符在原字符串中相差 2*(curRow - 1)
     */
    public String convert(String s, int numRows) {
        // 如果行数为1则不需要变换
        if (numRows == 1) {
            return s;
        }
        // 保存Z型变换后字符的数组
        char[] chars = new char[s.length()];
        // 字符索引位置
        int index = 0;
        // 当前行数
        int curRow = 1;
        // 逐行输出字符到数组中
        while (curRow <= numRows && index < s.length()) {
            int sIndex = curRow - 1;
            while (sIndex < s.length()) {
                // V型
                if (curRow != numRows) {
                    chars[index] = s.charAt(sIndex);
                    index++;
                    sIndex += 2 * (numRows - curRow);
                }
                // 倒V型
                if (curRow != 1 && sIndex < s.length()) {
                    chars[index] = s.charAt(sIndex);
                    index++;
                    sIndex += 2 * (curRow - 1);
                }
            }
            // 遍历下一行
            curRow++;
        }
        // 输出新的字符串
        return new String(chars);
    }

    @Test
    public void test1() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String newStr = convert(s, numRows);
        Assertions.assertEquals("PAHNAPLSIIGYIR", newStr);
    }

    @Test
    public void test2() {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        String newStr = convert(s, numRows);
        Assertions.assertEquals("PINALSIGYAHRPI", newStr);
    }

    @Test
    public void test3() {
        String s = "A";
        int numRows = 1;
        String newStr = convert(s, numRows);
        Assertions.assertEquals("A", newStr);
    }

}
