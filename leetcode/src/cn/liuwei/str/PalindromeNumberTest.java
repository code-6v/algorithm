package cn.liuwei.str;

public class PalindromeNumberTest {

    public boolean isPalindrome(int x) {
        // 负数和个位是0的数不是回文数
        if (x < 0 || x % 10 == 0) {
            return false;
        }

        String s = String.valueOf(x);
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }

}
