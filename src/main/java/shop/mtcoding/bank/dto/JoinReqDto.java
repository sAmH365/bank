package shop.mtcoding.bank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

@Getter
@Setter
public class JoinReqDto {
    private String username;
    private String password;
    private String email;
    private String fullName;

    public User toEntity() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .fullname(fullName)
                .role(UserEnum.CUSTOMER)
                .build();
    }
}