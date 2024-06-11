package shop.mtcoding.bank.ex;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CustomForbiddenException extends RuntimeException {
    public CustomForbiddenException(String message) {
        super(message);
    }
}
