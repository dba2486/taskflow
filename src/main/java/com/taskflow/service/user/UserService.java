package com.taskflow.service.user;

import com.taskflow.dto.user.UserRegisterRequest;
import com.taskflow.dto.user.UserResponse;

public interface UserService {

    /**
     * 사용자 등록
     */
    UserResponse register(UserRegisterRequest request);

    /**
     * 사용자 조회 (id 기반)
     */
    UserResponse getUser(Long userId);
}
