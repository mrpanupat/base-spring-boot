package io.github.mrpanupt.baseproject.logger;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class LoggerControllerInterceptor {

    private final LoggerService loggerService;

//    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
//            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
//            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
//            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)" +
//            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
//            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)")
//    public Object interceptController(ProceedingJoinPoint joinPoint) throws Throwable {
//        loggerService.logRequest();
//        return joinPoint.proceed();
//    }

}
