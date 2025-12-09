package com.taskflow.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    private String name;

    @Size(min = 4, max = 100, message = "비밀번호는 4~100자여야 합니다.")
    private String password;

    @Builder
    public UserUpdateRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
