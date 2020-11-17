package com.sys.manage.common.annotation;

import com.alibaba.fastjson.JSON;
import com.sys.manage.common.utils.ObjectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LY on 2019/4/09.
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.sys.manage.common.annotation.Log)")
    private void cut() { }


    @Around("cut()")
    public Object advice(ProceedingJoinPoint joinPoint) throws  Throwable {
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        signature.getName();
        //AOP代理类的名字
        signature.getDeclaringTypeName();
        String className = signature.getDeclaringTypeName().split("[.]")[signature.getDeclaringTypeName().split("[.]").length - 1];
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isEmpty(requestAttributes)) {
            Object result = joinPoint.proceed();
            return result;
        }
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        if (ObjectUtil.isEmpty(request)) {
            Object result = joinPoint.proceed();
            return result;
        }
        String method = request.getMethod();
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        if ("get".equalsIgnoreCase(request.getMethod())) {
            Enumeration<String> enumeration = request.getParameterNames();
            Map<String, String> parameterMap = new HashMap<>();
            while (enumeration.hasMoreElements()) {
                String parameter = enumeration.nextElement();
                parameterMap.put(parameter, request.getParameter(parameter));
            }
            String str = JSON.toJSONString(parameterMap);
            if (obj.length > 0) {
                logger.info(className + "|" + signature.getName() + "|GET|" + str);
            }
        }
        if ("post".equalsIgnoreCase(request.getMethod())) {
            try {
                BufferedReader br = request.getReader();
                String str = null;
                StringBuffer stringBuffer = new StringBuffer();
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                logger.info(className + "|" + signature.getName() + "|POST|" + stringBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Object result = joinPoint.proceed();
        if(ObjectUtil.isNotEmpty(result)){
            logger.info(className + "|" + signature.getName() + "|" + method + "|" + result.toString());
        }
        return result;

    }

}
