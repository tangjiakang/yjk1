package com.tjk.apps.cms.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tjk.apps.cms.bean.extend.User;
import com.tjk.apps.cms.comment.Base64Util;
import com.tjk.apps.cms.comment.Message;
import com.tjk.apps.cms.comment.MessageUtil;
import com.tjk.apps.cms.dao.extend.UserVM;
import com.tjk.apps.cms.service.impl.BaseUserSerciceImpl;
@RequestMapping("/test")
@RestController
public class LoginController{

	@Autowired
	Base64Util code;
	
	@Autowired
	BaseUserSerciceImpl busi;
	@PostMapping(value="login")
	public Message login(@RequestBody UserVM vm)  {
		 User login;
		try {
			login = busi.login(vm);
			String token = Base64Util.encode("ASDASDCCSDQFGL"+login.getRoles().get(0).getrId().toString());
			login.getRoles().get(0).setrId(0);
			login.setuId(0);
			Message success = MessageUtil.success(token);
			success.setData(login);
			return success;
		} catch (Exception e) {
			return MessageUtil.error(e.getMessage());
		}
	}
}
