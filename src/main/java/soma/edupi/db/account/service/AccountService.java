package soma.edupi.db.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupi.db.account.domain.Account;
import soma.edupi.db.account.exception.InvalidInputException;
import soma.edupi.db.account.models.request.EmailRequest;
import soma.edupi.db.account.models.request.LoginRequest;
import soma.edupi.db.account.models.request.SignupRequest;
import soma.edupi.db.account.models.response.LoginResponse;
import soma.edupi.db.account.repository.AccountRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveAccount(SignupRequest signupRequest) {
        // 이메일 중복 체크
        if (accountRepository.existsByEmail(signupRequest.getEmail())) {
            throw new InvalidInputException("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        }
        Account account = signupRequest.toEntity();
        account.encodePassword(passwordEncoder); // 비밀번호 암호화

        accountRepository.save(account);
    }

    public LoginResponse findAccountByEmail(LoginRequest loginRequest) {
        Account findAccount = accountRepository.findAccountByEmail(loginRequest.getEmail()).orElseThrow(
            // 이메일이 일치하지 않는 경우
            () -> new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다.")
        );

        // 비밀번호가 일치하지 않는 경우
        if (!passwordEncoder.matches(loginRequest.getPassword(), findAccount.getPassword())) {
            throw new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다.");
        }

        return LoginResponse.of(findAccount);
    }

    @Transactional
    public String activateAccount(EmailRequest emailRequest) {
        // 이메일이 일치하는 유저 찾기
        Account account = accountRepository.findAccountByEmail(emailRequest.getEmail())
            .orElseThrow(() -> {
                    log.info("email activateAccount fail, email = {} ", emailRequest.getEmail());
                    return new InvalidInputException("존재하지 않는 회원입니다.");
                }
            );
        // 계정 활성
        account.activate();
        accountRepository.save(account);

        return account.getEmail();
    }

}
