package cn.liuwei.str;

import org.junit.jupiter.api.Test;

public class ReverseIntegerTest {

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int num = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && 10 * res > Integer.MAX_VALUE - num)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && 10 * res < Integer.MIN_VALUE - num)) {
                return 0;
            }
            res = (10 * res + num);
            x /= 10;
        }
        return res;
    }

    @Test
    public void test1() {
        int x = 123;
        int reverse = reverse(x);
        System.out.println(reverse);
    }

}
