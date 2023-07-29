package com.blog.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class Login {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String password;
}
