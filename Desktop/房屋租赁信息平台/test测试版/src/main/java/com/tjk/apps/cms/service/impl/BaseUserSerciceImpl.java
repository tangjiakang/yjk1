package com.tjk.apps.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.extend.Role;
import com.tjk.apps.cms.bean.extend.User;
import com.tjk.apps.cms.dao.BaseUserMapper;
import com.tjk.apps.cms.dao.extend.UserExtend;
import com.tjk.apps.cms.dao.extend.UserVM;
import com.tjk.apps.cms.service.BaseUserService;
import com.tjk.apps.cms.service.MenuService;


@Service
public class BaseUserSerciceImpl implements BaseUserService{

	@Autowired
	private UserExtend u;
	@Autowired
	private MenuService m;
	
	@Override
	public User login(@RequestBody UserVM userVM) throws Exception {
		System.out.println(userVM.getUsername());
		User user = u.SelectByCount(userVM.getUsername());
		
		if(user == null) {
			 throw new Exception("该用户不存在");
		}
		else {
			if(user.getPassword().equals(userVM.getPassword())) {
				System.out.println("测试");
				List<Role> roles = user.getRoles();
			    if(roles == null) {
			    	System.out.println(roles);
			    }
				Role role = roles.get(0);
				user.setMenus(m.selectByRoleId(role.getrId()));
				return user;
			}else {
				throw new Exception("密码错误");
			}
		}
		
	}

	@Autowired
	BaseUserMapper bum;
	@Override
	public void SaveOrUpdate(BaseUser baseUser) throws Exception {
		
		
	}
	
}
