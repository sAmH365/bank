package shop.mtcoding.bank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;
import shop.mtcoding.bank.dto.user.UserResDto.JoinResDto;
import shop.mtcoding.bank.ex.CustomApiException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public JoinResDto joinUser(JoinReqDto reqDto) {
        // 1.동일 유저네임 존재 검사
        Optional<User> userOp = userRepository.findByUsername(reqDto.getUsername());
        if (userOp.isPresent()) {
            throw new CustomApiException("동일한 username이 존재합니다.");
        }
        // 2.패스워드 인코딩 + 회원가입
        User user = userRepository.save(reqDto.toEntity());

        // 3.dto 응답
        return new JoinResDto(user);
    }
}
