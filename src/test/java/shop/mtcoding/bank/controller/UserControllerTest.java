package shop.mtcoding.bank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import shop.mtcoding.bank.config.dummy.DummyObject;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest extends DummyObject {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;
    @Autowired private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        dataSetting();
    }

    private void dataSetting() {
        userRepository.save(newUser("user99", "fullUser"));
    }

    @Test
    void join_test() throws Exception {
        // given
        JoinReqDto reqDto = new JoinReqDto();
        reqDto.setUsername("user2");
        reqDto.setPassword("1234");
        reqDto.setEmail("user2Test@naver.com");
        reqDto.setFullName("userFullName");

        String json = mapper.writeValueAsString(reqDto);

        // expected
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("user2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName").value("userFullName"));
    }

    @Test
    void join_fail_test() throws Exception {
        // given
        JoinReqDto reqDto = new JoinReqDto();
        reqDto.setUsername("user99");
        reqDto.setPassword("1234");
        reqDto.setEmail("user99Test@naver.com");
        reqDto.setFullName("userFullName");

        String json = mapper.writeValueAsString(reqDto);

        // expected
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(-1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
    }
}
