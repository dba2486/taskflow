package com.taskflow.service.user;

import com.taskflow.dto.user.UserRegisterRequest;
import com.taskflow.dto.user.UserResponse;
import com.taskflow.dto.user.UserUpdateRequest;

public interface UserService {

    /**
     * 사용자 등록
     */
    UserResponse register(UserRegisterRequest request);

    /**
     * 사용자 조회 (id 기반)
     */
    UserResponse getUser(Long userId);

    /**
     * 사용자 정보 수정
     */
    UserResponse updateUser(Long userId, UserUpdateRequest request);

    /**
     * 사용자 삭제
     */
    void deleteUser(Long userId);

    /**
     * 로그인한 사용자의 내 정보 조회
     */
    UserResponse getMyInfo(Long userId);
}
