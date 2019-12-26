package com.sys.manage.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 配置拦截器
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // axios跨域问题修改
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                // 白名单不拦截，可以做成list维护
                .excludePathPatterns("/sys/login") //排除登录操作
                .excludePathPatterns("**/*.js","**/.css","**/*.img"); //排除样式、脚本、图片等资源文件
    }
}