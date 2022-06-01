package com.example.testlogproject.business.advice;

import com.example.testlogproject.business.abstracts.RequestService;
import com.example.testlogproject.business.concrates.UserServiceManager;
import com.example.testlogproject.business.constants.testLog;
import com.example.testlogproject.entities.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Aspect
@Component
public class TestLogAdvice {

    Logger logger = LoggerFactory.getLogger(TestLogAdvice.class);

    //    https://stackoverflow.com/questions/977714/accessing-httpservletrequest-from-aop-advice-in-spring-2-5-with-annotations
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RequestService requestService;


    @Around("@annotation(testLog)")
    public Object testLogBusiness(ProceedingJoinPoint joinPoint, testLog testLog) throws Throwable {
        Object object = joinPoint.proceed();
//        UUID transactionId = UUID.randomUUID();
        HttpSession session = request.getSession();
        session.setAttribute("Object", object);
        logger.info("Transactional Id: " + UserServiceManager.randomIdGenerator());
        logger.info("Ip Address: " + requestService.getClientIp(request));
        logger.info("URL: " + String.valueOf(request.getRequestURL()));
        logger.info("Request's session: " + request.getSession());
        logger.info("Session Id: " + session.getId());
        logger.info("Session Attributes: " + session.getAttribute("Object"));
        logger.info("Session: " + String.valueOf(session.getServletContext()));
        return object;
    }




}