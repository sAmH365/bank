package shop.mtcoding.bank.domain.transaction;

import lombok.Getter;

@Getter
public enum TransactionEnum {
    WITHDRAW("출금"), DEPOSIT("입금"), TRANSFER("이체"), ALL("입출금내역");

    private String value;

    TransactionEnum(String value) {
        this.value = value;
    }
}
