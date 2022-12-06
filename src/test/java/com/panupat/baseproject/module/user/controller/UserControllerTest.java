package com.panupat.baseproject.module.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panupat.baseproject.exception.ExceptionHandling;
import com.panupat.baseproject.module.user.model.UserRegisterRequest;
import com.panupat.baseproject.module.user.model.UserResponse;
import com.panupat.baseproject.module.user.service.UserService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith({MockitoExtension.class})
class UserControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = standaloneSetup(new UserController(userService))
                .setControllerAdvice(new ExceptionHandling())
                .build();
    }

    @Test
    void register_Success() throws Exception {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setEmail("a@a.a");
        request.setPassword("12345");
        request.setFirstName("a");
        request.setLastName("a");

        when(userService.register(any()))
                .thenReturn(mockUserResponse());

        RequestBuilder requestBuilder = post("/v1/user/register")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status.code", is("0")));
        resultActions.andExpect(jsonPath("$.status.message", containsString("success")));
    }

    @Test
    void getUserInfo_Success() throws Exception {
        when(userService.getUserInfo(anyLong()))
                .thenReturn(mockUserResponse());

        RequestBuilder requestBuilder = get("/v1/user/1");

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status.code", is("0")));
        resultActions.andExpect(jsonPath("$.status.message", containsString("success")));
    }

    private UserResponse mockUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setEmail("a@a.a");
        userResponse.setFirstName("a");
        userResponse.setLastName("a");
        return userResponse;
    }
}
