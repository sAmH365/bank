package shop.mtcoding.bank.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import shop.mtcoding.bank.dto.ResponseDto;

import java.io.IOException;

public class CustomResponseUtil {

    public static void unAuthentication(HttpServletResponse response, String msg) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
        String json = mapper.writeValueAsString(responseDto);

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(401);
        response.getWriter().println(json);
    }
}
