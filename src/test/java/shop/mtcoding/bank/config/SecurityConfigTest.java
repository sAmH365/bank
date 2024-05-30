package shop.mtcoding.bank.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void authentication_test() throws Exception {
    // expected
    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/s/hello"))
//            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(-1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("로그인이 필요합니다."))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/s/hello")).andDo(MockMvcResultHandlers.print());
//        MockHttpServletResponse response = resultActions.andReturn().getResponse();
//        int status = response.getStatus();
    }

    @Test
    void authorization_test() throws Exception {
        // expected
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/hello"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(-1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("로그인이 필요합니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
    }
}
