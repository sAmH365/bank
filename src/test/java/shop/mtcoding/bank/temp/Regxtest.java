package shop.mtcoding.bank.temp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class Regxtest {
    @Test
    void 한글만됨_test() {
        String pattern1 = "^[ㄱ-ㅎ가-힣]+$";
        String pattern2 = "^[ㄱ-ㅎ가-힣]*$";

        String value1 = "가나다";
        String value2 = "";
        boolean result1 = Pattern.matches(pattern1, value1);
        boolean result2 = Pattern.matches(pattern2, value2);

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
    }

    @Test
    void 한글은안됨_test() {
        String pattern = "^[^ㄱ-ㅎ가-힣]+$";

        String value1 = "가나다";
        String value2 = "1234";
        String value3 = "abc";

        boolean result1 = Pattern.matches(pattern, value1);
        boolean result2 = Pattern.matches(pattern, value2);
        boolean result3 = Pattern.matches(pattern, value3);

        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isTrue();
    }

    @Test
    void 영어만됨_test() {
        String pattern = "^[a-zA-Z]+$";

        String value1 = "가나다";
        String value2 = "1234";
        String value3 = "abc";

        boolean result1 = Pattern.matches(pattern, value1);
        boolean result2 = Pattern.matches(pattern, value2);
        boolean result3 = Pattern.matches(pattern, value3);

        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isTrue();
    }

    @Test
    void 영어는안됨_test() {
        String pattern = "^[^a-zA-Z]*$";

        String value1 = "가나다";
        String value2 = "1234";
        String value3 = "abc";
        String value4 = "";

        boolean result1 = Pattern.matches(pattern, value1);
        boolean result2 = Pattern.matches(pattern, value2);
        boolean result3 = Pattern.matches(pattern, value3);
        boolean result4 = Pattern.matches(pattern, value4);

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isFalse();
        Assertions.assertThat(result4).isTrue();
    }

    @Test
    void 영어와숫자만됨_test() {
        String pattern = "^[0-9a-zA-Z]+$";

        String value1 = "가나다";
        String value2 = "1234";
        String value3 = "abc";
        String value4 = "";
        String value5 = "asdf24124";
        String value6 = "asdf24124ㅎ";

        boolean result1 = Pattern.matches(pattern, value1);
        boolean result2 = Pattern.matches(pattern, value2);
        boolean result3 = Pattern.matches(pattern, value3);
        boolean result4 = Pattern.matches(pattern, value4);
        boolean result5 = Pattern.matches(pattern, value5);
        boolean result6 = Pattern.matches(pattern, value6);

        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isTrue();
        Assertions.assertThat(result4).isFalse();
        Assertions.assertThat(result5).isTrue();
        Assertions.assertThat(result6).isFalse();
    }

    @Test
    void 영어만되고길이는최소2최대4이다_test() {
        String pattern = "^[a-zA-Z]{2,4}$";

        String value1 = "가나다";
        String value2 = "1234";
        String value3 = "abc";
        String value4 = "";
        String value5 = "a";
        String value6 = "abcde";
        String value7 = "abcd";

        boolean result1 = Pattern.matches(pattern, value1);
        boolean result2 = Pattern.matches(pattern, value2);
        boolean result3 = Pattern.matches(pattern, value3);
        boolean result4 = Pattern.matches(pattern, value4);
        boolean result5 = Pattern.matches(pattern, value5);
        boolean result6 = Pattern.matches(pattern, value6);
        boolean result7 = Pattern.matches(pattern, value7);

        Assertions.assertThat(result1).isFalse();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isTrue();
        Assertions.assertThat(result4).isFalse();
        Assertions.assertThat(result5).isFalse();
        Assertions.assertThat(result6).isFalse();
        Assertions.assertThat(result7).isTrue();
    }

    // username, email, fullname
}
