package shop.mtcoding.bank.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.mtcoding.bank.config.dummy.DummyObject;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;
import shop.mtcoding.bank.dto.user.UserResDto.JoinResDto;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends DummyObject {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void 회원가입_test() {
        // given
        JoinReqDto reqDto = new JoinReqDto();
        reqDto.setUsername("username1");
        reqDto.setPassword("1234");
        reqDto.setEmail("test@naver.com");
        reqDto.setFullName("fullUserName1");

        //stub
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
//        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.of(new User()));

        User user1 = newMockUser(1L, "username1", "fullUserName");
        Mockito.when(userRepository.save(any())).thenReturn(user1);

        // when
        JoinResDto joinResDto = userService.joinUser(reqDto);

        // then
        Assertions.assertThat(joinResDto.getId()).isEqualTo(1L);
        Assertions.assertThat(joinResDto.getUsername()).isEqualTo("username1");
    }
}
