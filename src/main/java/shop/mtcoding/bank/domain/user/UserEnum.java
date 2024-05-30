package shop.mtcoding.bank.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum UserEnum {
    ADMIN("관리자"), CUSTOMER("고객");

    private String value;

    UserEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
