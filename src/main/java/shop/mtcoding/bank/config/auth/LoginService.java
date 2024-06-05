package shop.mtcoding.bank.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 시큐리티로 로그인이 될때, 시큐리티가 loadUserByUsername() 실행해서 실제 존재하는 유저인지 체크
     * 있으면 ->  정상적으로 시큐리티 컨텍스트 내부 세션에 로그인된 세션이 만들어진다.
     * 없으면 -> InternalAuthenticationServiceException() 으로 제어 가능
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            return new InternalAuthenticationServiceException("인증실패");
        });

        return new LoginUser(user);
    }
}
