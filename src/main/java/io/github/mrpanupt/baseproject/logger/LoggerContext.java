package io.github.mrpanupt.baseproject.logger;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@Component
@RequestScope
public class LoggerContext {
    private long startTime = 0;
    private Class<?> responseClass;
}
