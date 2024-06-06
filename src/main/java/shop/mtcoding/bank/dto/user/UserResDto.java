package shop.mtcoding.bank.dto.user;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.util.CustomDateUtil;

import java.time.LocalDateTime;

public class UserResDto {

    @Getter
    @Setter
    public static class LoginResDto {
        private Long id;
        private String username;
        private String createdAt;

        public LoginResDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.createdAt = CustomDateUtil.toStringFormat(user.getCreatedAt());
        }
    }

    @Getter
    @Setter
    public static class JoinResDto {
        private Long id;
        private String username;
        private String fullName;

        public JoinResDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.fullName = user.getFullname();
        }
    }
}
