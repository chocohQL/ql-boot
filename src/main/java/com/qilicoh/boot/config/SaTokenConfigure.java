package com.qilicoh.boot.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.qilicoh.boot.domain.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.qilicoh.boot.common.constant.Constants.*;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer, StpInterface {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 登录校验
            SaRouter.match("/**")
                    .notMatch(SaHttpMethod.OPTIONS)
                    .notMatch(
                            "/user/login",
                            "/user/register",
                            "/user/captcha"
                    )
                    .check(r -> StpUtil.checkLogin());
            // 权限校验
            SaRouter.match("/admin", r -> StpUtil.checkRole(ROLE_ADMIN));
        })).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        // 登录用户角色校验
        User loginUser = JSON.parseObject(o.toString(), User.class);
        return CollUtil.newArrayList(loginUser.getRole());
    }

    @Override
    public List<String> getPermissionList(Object o, String s) {
        return null;
    }
}
