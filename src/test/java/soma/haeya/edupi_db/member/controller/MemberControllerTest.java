package soma.haeya.edupi_db.member.controller;

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
import soma.haeya.edupi_db.member.dto.request.LoginRequest;
import soma.haeya.edupi_db.member.dto.response.LoginResponse;
import soma.haeya.edupi_db.member.service.MemberService;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    MemberService memberService;

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "asdf", "asdf@naver", "asdf.com"})
    @DisplayName("잘못된 email 형식으로 로그인")
    void loginWithWrongEmailFormat(String email) throws Exception {
        LoginRequest loginRequest = new LoginRequest(email, "asdf1234");

        mockMvc.perform(post("/member/login")
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

        mockMvc.perform(post("/member/login")
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

        when(memberService.findMemberByEmailAndPassword(loginRequest))
            .thenReturn(response);

        mockMvc.perform(post("/member/login")
            .content(mapper.writeValueAsString(loginRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}