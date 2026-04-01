package com.taskflow.service.auth;

import com.taskflow.dto.auth.LoginRequest;
import com.taskflow.dto.auth.LoginResponse;

public interface AuthService {

    /**
     * 사용자 로그인
     */
    LoginResponse login(LoginRequest request);
}
