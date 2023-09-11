package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 비밀번호는 최소 8자 이상 12자 이하
 * 8자 미만 또는 12자 초과 인 경우 IllegalArgumentException 예외 발생
 * 경계조건에 대해 테스트 코드를 작성해야한다
 */
public class PasswordValidTest {
    @DisplayName("비밀번호는 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다.")
    @Test
    void validPasswordTest() {
        assertThatCode(() -> PasswordValidator.validate("serverwizard")).doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만 또는 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다..")
    @ParameterizedTest
    @ValueSource(strings = {"aabbcc","aabbccddffqqq"})
    void validPasswodTest2(String pwd) {
        assertThatCode(() -> PasswordValidator.validate(pwd))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("비밀번호는 최소 8자 최대 12자 이하여야 한다.");
    }
}
