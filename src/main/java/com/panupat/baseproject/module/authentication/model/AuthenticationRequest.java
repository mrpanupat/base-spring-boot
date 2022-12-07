package com.panupat.baseproject.module.authentication.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Valid
@Getter
@Setter
public class AuthenticationRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
