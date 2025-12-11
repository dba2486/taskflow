package com.taskflow.service.user;

import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import com.taskflow.dto.user.UserRegisterRequest;
import com.taskflow.dto.user.UserResponse;
import com.taskflow.dto.user.UserUpdateRequest;
import com.taskflow.global.exception.CustomException;
import com.taskflow.global.exception.ErrorCode;
import com.taskflow.service.common.EntityFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityFinder finder;

    @Override
    @Transactional
    public UserResponse register(UserRegisterRequest request) {

        // email 중복 체크 (추가 구현 예정)
        if (userRepository.existByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())    // TODO: password 암호화
                .build();

        User saved = userRepository.save(user);
        return UserResponse.from(saved);
    }

    @Override
    public UserResponse getUser(Long userId) {

        User user = finder.getUser(userId);

        return UserResponse.from(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {

        User user = finder.getUser(userId);

        // 이름 변경
        user.updateName(request.getName());

        // 비밀번호 변경
        user.updatePassword(request.getPassword());

        return UserResponse.from(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {

        User user = finder.getUser(userId);

        userRepository.delete(user);
    }
}
