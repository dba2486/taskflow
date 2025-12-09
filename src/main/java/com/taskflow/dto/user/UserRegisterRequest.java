package com.taskflow.dto.user;

import com.taskflow.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, max = 100, message = "비밀번호는 4~100자여야 합니다.")
    private String password;

    @Builder
    public UserRegisterRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User to(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

}
