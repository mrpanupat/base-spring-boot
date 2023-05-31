package io.github.mrpanupt.baseproject.module.authentication.controller;

import io.github.mrpanupt.baseproject.module.BaseResponse;
import io.github.mrpanupt.baseproject.module.authentication.model.AuthenticationRequest;
import io.github.mrpanupt.baseproject.module.authentication.model.AuthenticationResponse;
import io.github.mrpanupt.baseproject.module.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/v1/authenticate")
    public BaseResponse<AuthenticationResponse> authenticate(@RequestBody @Validated AuthenticationRequest request) {
        log.info("===== Start: authentication =====");
        String token = authenticationService.authenticate(request.getEmail(), request.getPassword());
        log.info("===== End: authentication =====");
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        return BaseResponse.success(response);
    }
}
