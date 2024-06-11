package shop.mtcoding.bank.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import shop.mtcoding.bank.dto.ResponseDto;

import java.io.IOException;

public class CustomResponseUtil {

    public static void success(HttpServletResponse response, Object dto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseDto<?> responseDto = new ResponseDto<>(1, "로그인성공", dto);
        String json = mapper.writeValueAsString(responseDto);

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(200);
        response.getWriter().println(json);
    }

    public static void fail(HttpServletResponse response, String msg, HttpStatus status) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
        String json = mapper.writeValueAsString(responseDto);

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(status.value());
        response.getWriter().println(json);
    }
}
