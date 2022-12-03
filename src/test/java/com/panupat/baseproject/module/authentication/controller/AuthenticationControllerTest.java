package com.panupat.baseproject.module.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panupat.baseproject.exception.ExceptionHandling;
import com.panupat.baseproject.module.authentication.model.AuthenticationRequest;
import com.panupat.baseproject.module.authentication.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith({MockitoExtension.class})
class AuthenticationControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private AuthenticationService authenticationService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = standaloneSetup(new AuthenticationController(authenticationService))
                .setControllerAdvice(new ExceptionHandling())
                .build();
    }

    @Test
    void authenticate_Success() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("a@a.a");
        request.setPassword("12345");

        RequestBuilder requestBuilder = post("/v1/authenticate")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status.code", is("0")));
        resultActions.andExpect(jsonPath("$.status.message", containsString("success")));
    }
}
