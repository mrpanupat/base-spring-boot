package io.github.mrpanupt.baseproject.module.probe.controller;

import io.github.mrpanupt.baseproject.exception.ExceptionHandling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith({MockitoExtension.class})
class ProbeControllerTest {

    @Mock
    private HealthIndicator pingHealthContributor;
    @Mock
    private InfoEndpoint infoEndpoint;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = standaloneSetup(new ProbeController(pingHealthContributor, infoEndpoint))
                .setControllerAdvice(new ExceptionHandling())
                .build();
    }

    @Test
    void getHealthIndicator_Success() throws Exception {
        when(pingHealthContributor.getHealth(anyBoolean()))
                .thenReturn(new Health.Builder().up().build());

        RequestBuilder requestBuilder = get("/health");

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status", is("UP")));
    }

    @Test
    void getInfo_Success() throws Exception {
        when(infoEndpoint.info())
                .thenReturn(new HashMap<>());

        RequestBuilder requestBuilder = get("/info");

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().is2xxSuccessful());
    }
}
