package shop.mtcoding.bank.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bank.domain.user.User;

@Getter
@Setter
public class JoinResDto {
    private Long id;
    private String username;
    private String fullName;

    public JoinResDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullname();
    }
}
