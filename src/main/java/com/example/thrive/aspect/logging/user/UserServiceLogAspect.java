package com.example.thrive.aspect.logging.user;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class UserServiceLogAspect {

    @Before("execution(* com.example.thrive.user.UserService.*(..))")
    public void userLogin(JoinPoint joinPoint) {
        log.info("{} called with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.example.thrive.user.UserService.*(..))", returning = "response")
    public void userLogReturn(JoinPoint joinPoint, ResponseEntity<String> response) {
        log.info("{} returned status: {}, body: {}", joinPoint.getSignature(), response.getStatusCode(), response.getBody());
    }

}
