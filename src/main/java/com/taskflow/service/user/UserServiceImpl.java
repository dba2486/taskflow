package com.taskflow.service.user;

import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import com.taskflow.dto.user.UserRegisterRequest;
import com.taskflow.dto.user.UserResponse;
import com.taskflow.global.exception.CustomException;
import com.taskflow.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse register(UserRegisterRequest request) {

        // email 중복 체크 (추가 구현 예정)
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())    // TODO: password 암호화
                .build();

        User saved = userRepository.save(user);
        return new UserResponse(saved);
    }

    @Override
    public UserResponse getUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new UserResponse(user);
    }
}
