package soma.edupimeta.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupimeta.account.exception.InvalidInputException;
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

    public void signup(SignupRequest signupRequest) {
        Account account = signupRequest.toEntity();

        // 이메일 중복 체크
        if (isExists(account.getEmail())) {
            throw new InvalidInputException("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        account.encodePassword();
        addAccount(account);
    }

    @Transactional(readOnly = true)
    public Account login(String email, String password) {
        Account account = getMember(email);

        // 비밀번호가 일치하지 않는 경우
        if (validatePassword(password, account.getPassword())) {
            throw new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다.");
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
            // Todo 예외 정의
            () -> new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다.")
        );
    }

    private boolean validatePassword(String inputPassword, String originalPassword) {
        return !passwordEncoder.matches(inputPassword, originalPassword);
    }

    private void addAccount(Account account) {
        accountRepository.save(account);
    }

}
