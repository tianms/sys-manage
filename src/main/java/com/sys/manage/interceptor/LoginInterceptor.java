package com.sys.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.sys.manage.common.constants.CacheKeyConstant;
import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.utils.R;
import com.sys.manage.config.EhcacheService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截
 *
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private EhcacheService ehcacheService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 验证用户是否登录
        String token = request.getHeader(Constant.SYS_CONSTANT.TOKEN);

        Object object = ehcacheService.get(CacheKeyConstant.USER_TOKEN_KEY + token);

        if (object == null || StringUtils.isBlank(token)) {

            // 相应信息返回
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers","Origin,Content-Type,Accept,token,X-Requested-With");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            String json = JSONObject.toJSONString(R.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
            response.getWriter().print(json);
            return false;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
