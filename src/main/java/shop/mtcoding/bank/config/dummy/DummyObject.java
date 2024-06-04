package shop.mtcoding.bank.config.dummy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

import java.time.LocalDateTime;

public class DummyObject {

    protected User newUser(String username, String fullName) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("1234");

        return User.builder()
                .username(username)
                .password(encodedPassword)
                .email(username + "@naver.com")
                .fullname(fullName)
                .role(UserEnum.CUSTOMER)
                .build();
    }

    protected User newMockUser(Long id, String username, String fullName) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("1234");

        return User.builder()
                .id(id)
                .username(username)
                .password(encodedPassword)
                .email(username + "@naver.com")
                .fullname(fullName)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(UserEnum.CUSTOMER)
                .build();
    }
}
