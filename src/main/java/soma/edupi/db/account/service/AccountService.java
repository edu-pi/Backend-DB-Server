package soma.edupi.db.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupi.db.account.domain.Account;
import soma.edupi.db.account.exception.InvalidInputException;
import soma.edupi.db.account.exception.ServerException;
import soma.edupi.db.account.models.request.LoginRequest;
import soma.edupi.db.account.models.request.SignupRequest;
import soma.edupi.db.account.models.response.LoginResponse;
import soma.edupi.db.account.repository.AccountRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveAccount(SignupRequest signupRequest) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(signupRequest.getEmail())) {
            throw new InvalidInputException("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        }
        try {
            Account member = signupRequest.toEntity();
            member.encodePassword(passwordEncoder); // 비밀번호 암호화

            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new ServerException("DB 저장 실패");
        } catch (Exception e) {
            throw new ServerException("알 수 없는 오류: " + e.getMessage());
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Account findMember = memberRepository.findMemberByEmail(loginRequest.getEmail()).orElseThrow(
            // 이메일이 일치하지 않는 경우
            () -> new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다.")
        );

        // 비밀번호가 일치하지 않는 경우
        if (!passwordEncoder.matches(loginRequest.getPassword(), findMember.getPassword())) {
            throw new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다.");
        }

        return LoginResponse.of(findMember);
    }

}
