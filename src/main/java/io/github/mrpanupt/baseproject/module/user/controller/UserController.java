package io.github.mrpanupt.baseproject.module.user.controller;

import io.github.mrpanupt.baseproject.module.BaseResponse;
import io.github.mrpanupt.baseproject.module.user.model.UserRegisterRequest;
import io.github.mrpanupt.baseproject.module.user.model.UserResponse;
import io.github.mrpanupt.baseproject.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/v1/user/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<UserResponse> register(@RequestBody UserRegisterRequest request) {
        log.info("===== Start: user register =====");
        UserResponse response = userService.register(request);
        log.info("===== End: user register =====");
        return BaseResponse.success(response);
    }

    @GetMapping(value = "/v1/user/{id}")
    public BaseResponse<UserResponse> getUserInfo(@PathVariable Long id) {
        log.info("===== Start: get user info =====");
        UserResponse response = userService.getUserInfo(id);
        log.info("===== End: get user info =====");
        return BaseResponse.success(response);
    }
}
