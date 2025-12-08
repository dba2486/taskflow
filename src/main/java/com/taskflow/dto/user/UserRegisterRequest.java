package com.taskflow.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegisterRequest {

    private String name;
    private String email;
    private String password;
}
