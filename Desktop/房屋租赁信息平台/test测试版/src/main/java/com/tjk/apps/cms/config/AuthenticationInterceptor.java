package com.tjk.apps.cms.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tjk.apps.cms.bean.extend.Menu;
import com.tjk.apps.cms.bean.extend.Role;
import com.tjk.apps.cms.comment.Base64Util;
import com.tjk.apps.cms.comment.Invocation;
import com.tjk.apps.cms.dao.BaseUserRoleMapper;
import com.tjk.apps.cms.dao.extend.RoleExtend;
import com.tjk.apps.cms.service.MenuService;
import com.tjk.apps.cms.service.impl.UserServiceImpl;
@Component
public class AuthenticationInterceptor implements HandlerInterceptor{
	@Autowired 
	MenuService mu;
	@Autowired
	RoleExtend us;
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    	System.out.println("拦截成功");
	    	// 防止 userService 注入不进来
		/*
		 * if (null == userService) { BeanFactory factory =
		 * WebApplicationContextUtils.getRequiredWebApplicationContext(request.
		 * getServletContext()); userService = (UserService)
		 * factory.getBean("userService"); }
		 */
	        // 验证权限
	        if (this.hasPermission(handler,request, mu,us)) {
	            return true;
	        }
	        //  null == request.getHeader("x-requested-with") TODO 暂时用这个来判断是否为ajax请求
	        // 如果没有权限 则抛403异常 springboot会处理，跳转到 /error/403 页面
	        response.sendError(HttpStatus.FORBIDDEN.value(), "无权限");
	        return false;
	    }

	    /**
	     * 是否有权限
	     */
	    private boolean hasPermission(Object handler,HttpServletRequest request,MenuService mu,RoleExtend us) {
	        if (handler instanceof HandlerMethod) {
	        	
	        	
	            HandlerMethod handlerMethod = (HandlerMethod) handler;
	            // 获取方法上的注解
	            Invocation requiredPermission = handlerMethod.getMethod().getAnnotation(Invocation.class);
	            // 如果方法上的注解为空 则获取类的注解
	            if (requiredPermission == null) {
	                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Invocation.class);
	            }
	            // 如果注解为null, 说明不需要拦截, 直接放过
	            if (requiredPermission == null) {
	                return true;
	            }
	            // 如果标记了注解，则判断权限
	            if (StringUtils.isNotBlank(requiredPermission.value())) {
	            	Object id = request.getHeader("Token");
	            	Base64Util code = new Base64Util();
	            	Integer uid = null ;
	             	Integer rid = null ;
	             	String tokent = Base64Util.decode((String)id);
	             	System.out.println(tokent);
	            	String sid = tokent.split("L")[1];
	            	Set<String> permissionSet  = new HashSet<>();
	            	if(id ==null) {
	            		System.out.println("id为空");
	            	}else {	
	            		uid = Integer.parseInt(sid);
	            	    List<Role> rs = us.selectByUserId(uid);
	            	    rid =rs.get(0).getrId();
	            	}
	            	System.out.println(rid);
	               List<Menu> Menu = mu.selectByRoleId(rid);
	               for (Menu menu1 : Menu) {
	            	   permissionSet.add(menu1.getUrl());
	            	   if(menu1.getChildren().size()>0) {
	            		   for (Menu menu2 : menu1.getChildren()) {
	            			   permissionSet.add(menu2.getUrl());
						}
	            	   }
				}
	                if (CollectionUtils.isEmpty(permissionSet) ){
	                    return false;
	                }
	                return permissionSet.contains(requiredPermission.value());
	            }
	        }
	        return true;
	    }

}
