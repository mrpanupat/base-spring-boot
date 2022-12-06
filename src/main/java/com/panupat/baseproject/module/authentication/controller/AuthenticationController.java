package com.panupat.baseproject.module.authentication.controller;

import com.panupat.baseproject.module.BaseResponse;
import com.panupat.baseproject.module.authentication.model.AuthenticationRequest;
import com.panupat.baseproject.module.authentication.model.AuthenticationResponse;
import com.panupat.baseproject.module.authentication.service.AuthenticationService;
import io.micrometer.observation.annotation.Observed;
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
    @Observed(name = "user.name",
            contextualName = "getting-user-name",
            lowCardinalityKeyValues = {"userType", "userType2"})
    public BaseResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("===== Start: authentication =====");
        String token = authenticationService.authenticate(request.getEmail(), request.getPassword());
        log.info("===== End: authentication =====");
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        return BaseResponse.success(response);
    }
}
