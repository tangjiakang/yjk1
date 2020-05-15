package com.tjk.apps.cms.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	AuthenticationInterceptor au;
    /*
     * 拦截器配置  
     * 在spring-mvc.xml配置文件内添加<mvc:interceptor>标签配置拦截器。
     */
	 @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 权限校验拦截器配置
        registry.addInterceptor(au).addPathPatterns("/**");

        
    }
}
