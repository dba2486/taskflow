package com.taskflow.api.user;

import com.taskflow.dto.user.UserRegisterRequest;
import com.taskflow.dto.user.UserResponse;
import com.taskflow.dto.user.UserUpdateRequest;
import com.taskflow.global.response.ApiResponse;
import com.taskflow.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /**
     * 사용자 등록
     */
    @PostMapping
    public ApiResponse<UserResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        return ApiResponse.success(userService.register(request));
    }


    /**
     * 사용자 조회 (id 기반)
     */
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long id) {
        return ApiResponse.success(userService.getUser(id));
    }

    /**
     * 사용자 정보 수정
     */
    @PatchMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        return ApiResponse.success(userService.updateUser(id, request));
    }

    /**
     * 사용자 삭제
     */
    @DeleteMapping("/{id}")
    public ApiResponse<UserResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.success(null);
    }

    /**
     * 로그인한 사용자의 내 정보 조회
     */
    @GetMapping("/me")
    public ApiResponse<UserResponse> getMyInfo(@AuthenticationPrincipal Long id) {
        return ApiResponse.success(userService.getMyInfo(id));
    }
}
