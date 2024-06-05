package shop.mtcoding.bank.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

public class UserReqDto {
    @Getter
    @Setter
    public static class JoinReqDto {
        @Pattern(regexp = "^[0-9a-zA-Z]{2,20}$", message = "영문/숫자 2~20자로 작성해주세요.")
        @NotEmpty
        private String username;

        @NotEmpty
        @Length(min = 4, max = 20)
        private String password;

        @NotEmpty
        @Email
        private String email;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z]{1,20}$", message = "영문/한글 1~20자로 작성해주세요.")
        @NotEmpty
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
}
