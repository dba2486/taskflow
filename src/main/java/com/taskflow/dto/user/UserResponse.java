package com.taskflow.dto.user;

import com.taskflow.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;
    private String name;
    private String email;

    @Builder
    public UserResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static UserResponse from (User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
