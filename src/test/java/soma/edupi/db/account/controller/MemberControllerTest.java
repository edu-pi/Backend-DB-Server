package soma.edupi.db.account.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import soma.edupi.db.account.exception.InvalidInputException;
import soma.edupi.db.account.models.request.LoginRequest;
import soma.edupi.db.account.models.request.SignupRequest;
import soma.edupi.db.account.models.response.LoginResponse;
import soma.edupi.db.account.service.AccountService;

@WebMvcTest(AccountController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AccountService memberService;

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "asdf", "asdf@naver", "asdf.com"})
    @DisplayName("잘못된 email 형식으로 로그인")
    void loginWithWrongEmailFormat(String email) throws Exception {
        LoginRequest loginRequest = new LoginRequest(email, "asdf1234");

        mockMvc.perform(post("/v1/member/findByEmailAndPassword")
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

        mockMvc.perform(post("/v1/member/findByEmailAndPassword")
            .content(mapper.writeValueAsString(loginRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("올바른 email, password로 로그인")
    void login() throws Exception {
        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "asdf1234!");
        LoginResponse response = new LoginResponse("asdf@naver.com", "홍길동", "ROLE_USER");

        when(memberService.login(loginRequest))
            .thenReturn(response);

        mockMvc.perform(post("/v1/member/findByEmailAndPassword")
            .content(mapper.writeValueAsString(loginRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입에 성공")
    void signUpSuccess() throws Exception {
        // given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("aabbcc@naver.com")
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        // Mocking
        doNothing().when(memberService).saveAccount(any(SignupRequest.class));

        // When & Then
        mockMvc.perform(post("/v1/member/signup")
                .content(mapper.writeValueAsString(signupRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("회원가입이 완료되었습니다."));
    }

    @Test
    @DisplayName("회원가입에 실패 - 이메일 중복")
    void signUpFail() throws Exception {
        // given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("aabbcc@naver.com")
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        // Mocking
        doThrow(InvalidInputException.class).when(memberService).saveAccount(any(SignupRequest.class));

        // When & Then
        mockMvc.perform(post("/v1/member/signup")
            .content(mapper.writeValueAsString(signupRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}