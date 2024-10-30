package soma.edupimeta.account.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import soma.edupimeta.account.service.domain.Account;
import soma.edupimeta.account.service.domain.AccountRole;

@Getter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupOauthRequest {

    @Email(message = "잘못된 이메일 형식입니다.")
    private String email;

    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
        message = "잘못된 형식입니다.")
    private String phoneNumber;

    public Account toEntity() {
        return Account.builder()
            .email(email)
            .password(generateRandomPassword())
            .name(name)
            .phoneNumber("010-0000-0000")
            .role(AccountRole.USER)
            .isEnabled(true)
            .isSocial(true)
            .build();
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString(); // 랜덤 비밀번호 생성
    }
}
