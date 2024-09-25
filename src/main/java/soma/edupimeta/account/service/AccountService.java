package soma.edupimeta.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupimeta.account.exception.AccountErrorCode;
import soma.edupimeta.account.exception.AccountException;
import soma.edupimeta.account.models.SignupRequest;
import soma.edupimeta.account.service.domain.Account;
import soma.edupimeta.account.service.repository.AccountRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account signup(SignupRequest signupRequest) {
        Account account = signupRequest.toEntity();

        // 이메일 중복 체크
        if (isExists(account.getEmail())) {
            throw new AccountException(AccountErrorCode.EMAIL_DUPLICATE);
        }

        account.encodePassword();
        return addAccount(account);
    }

    @Transactional(readOnly = true)
    public Account login(String email, String password) {
        Account account = getMember(email);

        // 비밀번호가 일치하지 않는 경우
        if (validatePassword(password, account.getPassword())) {
            throw new AccountException(AccountErrorCode.EMAIL_OR_PASSWORD_NOT_MATCH);
        }

        return account;
    }

    public String verifyAccountByEmail(String email) {
        Account account = getMember(email);

        // 계정 활성
        account.activate();

        return account.getEmail();
    }

    private boolean isExists(String email) {
        return accountRepository.existsByEmail(email);
    }

    private Account getMember(String email) {
        return accountRepository.findAccountByEmail(email).orElseThrow(
            () -> new AccountException(AccountErrorCode.EMAIL_NOT_MATCH)
        );
    }

    private boolean validatePassword(String inputPassword, String originalPassword) {
        return !passwordEncoder.matches(inputPassword, originalPassword);
    }

    private Account addAccount(Account account) {
        return accountRepository.save(account);
    }

}
