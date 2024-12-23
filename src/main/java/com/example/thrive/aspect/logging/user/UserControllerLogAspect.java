package com.example.thrive.aspect.logging.user;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class UserControllerLogAspect {

    @Before("execution(* com.example.thrive.user.UserController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("{} called with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

}
