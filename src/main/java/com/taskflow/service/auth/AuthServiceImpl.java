package com.taskflow.service.auth;

import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import com.taskflow.dto.auth.LoginRequest;
import com.taskflow.dto.auth.LoginResponse;
import com.taskflow.global.exception.CustomException;
import com.taskflow.global.exception.ErrorCode;
import com.taskflow.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest request) {

        // 조회
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);   // invalid password를 추가할 것
        }
        // JWT 발급
        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getEmail());

        return new LoginResponse(accessToken);
    }
}
