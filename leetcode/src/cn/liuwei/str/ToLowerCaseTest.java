package cn.liuwei.str;

import org.junit.jupiter.api.Test;

public class ToLowerCaseTest {

    public String toLowerCase(String s) {
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] >= 'A' && charArray[i] <= 'Z') {
                charArray[i] = (char) (charArray[i] + 32);
            }
        }
        return new String(charArray);
    }

    @Test
    public void test1() {

    }

}
