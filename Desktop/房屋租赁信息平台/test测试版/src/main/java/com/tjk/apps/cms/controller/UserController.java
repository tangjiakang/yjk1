package com.tjk.apps.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.extend.User;
import com.tjk.apps.cms.comment.Message;
import com.tjk.apps.cms.comment.MessageUtil;
import com.tjk.apps.cms.service.impl.UserServiceImpl;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserServiceImpl usvi;
	
	@PostMapping("getAll")
    public Message getAll() {
    	try {
			List<BaseUser> all = usvi.getAll();
			return MessageUtil.success(all);
		} catch (Exception e) {
			return MessageUtil.error("获取失败");
		}
    }
	
	@PostMapping("SaveOrUpdate")
	public Message saveOrUpdate(@Validated @RequestBody BaseUser u, BindingResult bindingResult)  {
		
		try {
			if (bindingResult.hasErrors()) {
	            StringBuilder sb = new StringBuilder();
	            for (FieldError fieldError : bindingResult.getFieldErrors()) {
	                sb.append(fieldError.getDefaultMessage()).append(",\n");
	            }
	            return MessageUtil.error(sb.toString()) ;
	        }			
			usvi.saveOrUpdate(u);
			return MessageUtil.success("");
		} catch (Exception e) {
			return MessageUtil.error("");
		}
	}
	@PostMapping("Delete")
	public Message deleteUser(@RequestBody BaseUser u) {
	   try {
		usvi.deleteUser(u.getuId());
		return MessageUtil.success("删除成功");
	} catch (Exception e) {
		return MessageUtil.error("删除失败");
	}	
	}
	@PostMapping("SelectByName")
	public List<BaseUser> selectByName(String name) {
		List<BaseUser> list = usvi.selectByNameOrAge("%"+name+"%");
		return list;
		
	}
	@PostMapping("SelectByCombination")
	public Message selectByCombination(@RequestBody BaseUser u) {
		
		try {
			 List<User> list= usvi.selectByCombination(u.getName(), u.getPhone(), u.getAddress());
			 return MessageUtil.success(list);
		} catch (Exception e) {
			return MessageUtil.error("失败");
		}
		
	}
}
