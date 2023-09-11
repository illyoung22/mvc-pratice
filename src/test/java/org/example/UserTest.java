package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @DisplayName("패스워드가 요구사항에 부합하지 않아 초기화가 되지 않는다.")
    @Test
    void passwordTest() {
        //given
        User user = new User();
        boolean isWrong = false;
        //when
        if (isWrong) {
            user.initPassword(new WrongFixedPasswordGenerator());
            //Then
            assertThat(user.getPassword()).isNull();
        }else {
            user.initPassword(new CorrectFixedPasswordGenerator());
            //Then
            assertThat(user.getPassword()).isNotNull();
        }
    }
}