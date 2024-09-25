package soma.edupimeta.account;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import soma.edupimeta.account.exception.AccountErrorCode;
import soma.edupimeta.account.exception.AccountException;
import soma.edupimeta.account.models.LoginRequest;
import soma.edupimeta.account.models.SignupRequest;
import soma.edupimeta.account.service.AccountService;
import soma.edupimeta.account.service.domain.Account;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AccountService accountService;

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "asdf", "asdf@naver", "asdf.com"})
    @DisplayName("잘못된 email 형식으로 로그인")
    void loginWithWrongEmailFormat(String email) throws Exception {
        LoginRequest loginRequest = new LoginRequest(email, "asdf1234");

        mockMvc.perform(post("/v1/account/login")
            .content(mapper.writeValueAsString(loginRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "asdf", "asdf1234"})
    @DisplayName("잘못된 password 형식으로 로그인")
    void loginWithWrongPasswordFormat(String password) throws Exception {
        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", password);

        mockMvc.perform(post("/v1/account/login")
            .content(mapper.writeValueAsString(loginRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("올바른 email, password로 로그인")
    void login() throws Exception {
        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "asdf1234!");
        Account account = Account.builder()
            .email("asdf@naver.com")
            .password("asdf1234!")
            .name("홍길동")
            .build();

        when(accountService.login(loginRequest.getEmail(), loginRequest.getPassword()))
            .thenReturn(account);

        mockMvc.perform(post("/v1/account/login")
            .content(mapper.writeValueAsString(loginRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입에 성공")
    void signupSuccess() throws Exception {
        // given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("aabbcc@naver.com")
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        Account account = Account.builder()
            .email("aabbcc@naver.com")
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        // Mocking
        when(accountService.signup(any(SignupRequest.class))).thenReturn(account);

        // When & Then
        mockMvc.perform(post("/v1/account/signup")
            .content(mapper.writeValueAsString(signupRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입에 실패 - 이메일 중복")
    void signupFail() throws Exception {
        // given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("aabbcc@naver.com")
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        doThrow(new AccountException(AccountErrorCode.EMAIL_DUPLICATE)).when(accountService)
            .signup(any(SignupRequest.class));

        // When & Then
        mockMvc.perform(post("/v1/account/signup")
            .content(mapper.writeValueAsString(signupRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict());
    }
}