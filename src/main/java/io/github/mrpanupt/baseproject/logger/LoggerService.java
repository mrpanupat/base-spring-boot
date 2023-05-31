package io.github.mrpanupt.baseproject.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Log4j2
@Service
public class LoggerService {

    private final LoggerContext loggerContext;
    private final ObjectMapper objectMapper;

    public LoggerService(LoggerContext loggerContext,
                         ObjectMapper objectMapper) {
        this.loggerContext = loggerContext;
        this.objectMapper = objectMapper.copy();
    }

    public void logRequest(Object body) {
        try {
            loggerContext.setStartTime(System.currentTimeMillis());
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String requestStr = "";
            if (body != null)
                requestStr = objectMapper.writeValueAsString(body);
            log.info("REQUEST method=[{}] path=[{}] body=[{}]", request.getMethod(), request.getServletPath(), requestStr);
        } catch (Exception e) {
            log.debug("Cannot log response body {}", e.getMessage());
            log.debug(e.getCause());
            //Ignore exception
        }
    }

    public void logResponse(ServletServerHttpResponse response, Object body) {
        try {
            log.info("RESPONSE [{}ms] status=[{}] body=[{}]",
                    System.currentTimeMillis() - loggerContext.getStartTime(),
                    response.getServletResponse().getStatus(),
                    objectMapper.writeValueAsString(body));
        } catch (Exception e) {
            log.debug("Cannot log response body {}", e.getMessage());
            log.debug(e.getCause());
            //Ignore exception
        }
    }
}
