package com.panupat.baseproject.module.authentication.controller;

import com.panupat.baseproject.module.BaseResponse;
import com.panupat.baseproject.module.authentication.model.AuthenticationRequest;
import com.panupat.baseproject.module.authentication.model.AuthenticationResponse;
import com.panupat.baseproject.module.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/v1/authenticate")
    public BaseResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("===== Start: authentication =====");
        String token = authenticationService.authenticate(request.getEmail(), request.getPassword());
        log.info("===== End: authentication =====");
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        return BaseResponse.success(response);
    }
}
