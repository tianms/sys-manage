package com.sys.manage.common.annotation;

import com.alibaba.fastjson.JSON;
import com.sys.manage.common.utils.IpAdrressUtil;
import com.sys.manage.modules.sys.entity.SysLogEntity;
import com.sys.manage.modules.sys.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Promise
 * @createTime 2018年12月18日 下午9:33:28
 * @description 切面日志配置
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(com.sys.manage.common.annotation.SysLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            insertLog(point, endTime - beginTime);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();

        //获取操作
        SysLog operation = method.getAnnotation(SysLog.class);
        if (operation != null) {
            String value = operation.value();
            sysLog.setOperation(value);//保存获取的操作
        }
//        Api api = method.getAnnotation(Api.class);
//        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
//        String operationText = "";
//        if (api != null) {
//            operationText += api.tags()[0];
//        }
//        if (apiOperation != null) {
//            operationText += '-' + apiOperation.value();
//        }
//        //保存获取的操作
//        sysLog.setOperation(operationText);

        //获取请求的类名
        String className = point.getTarget().getClass().getName();

        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = point.getArgs();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest httpRequest = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        if ("get".equalsIgnoreCase(httpRequest.getMethod())) {
            Enumeration<String> enumeration = httpRequest.getParameterNames();
            Map<String, String> parameterMap = new HashMap<>();
            while (enumeration.hasMoreElements()) {
                String parameter = enumeration.nextElement();
                parameterMap.put(parameter, httpRequest.getParameter(parameter));
            }
            String str = JSON.toJSONString(parameterMap);
            if (args.length > 0) {
                sysLog.setParams(str);
            }
        }
        if ("post".equalsIgnoreCase(httpRequest.getMethod())) {
            try {
                BufferedReader br = httpRequest.getReader();
                String str = null;
                StringBuffer stringBuffer = new StringBuffer();
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                sysLog.setParams(stringBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //请求的时间
//        sysLog.setCreateTime(new Date());

        //获取用户ID和企业ID
//        UserInfo userInfo = userContext.getUserInfo();
//        Integer userId = userInfo.getUserId();
//        sysLog.setUserId(userId);
//        String username = userInfo.getUsername();
//        sysLog.setUsername(username);
//        Integer CompanyId = userInfo.getCompanyId().intValue();
//        sysLog.setCompanyId(CompanyId);

        //获取用户ip地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        sysLog.setIp(IpAdrressUtil.getIpAdrress(request));

        sysLogService.insert(sysLog);
    }
}
