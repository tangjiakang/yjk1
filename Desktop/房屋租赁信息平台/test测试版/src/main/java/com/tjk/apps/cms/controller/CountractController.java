package com.tjk.apps.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjk.apps.cms.bean.BaseCountract;
import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.extend.countract;
import com.tjk.apps.cms.comment.Message;
import com.tjk.apps.cms.comment.MessageUtil;
import com.tjk.apps.cms.service.impl.Countract;

@RequestMapping("/countract")
@RestController
public class CountractController {
    @Autowired
	Countract cs;
	@PostMapping("getAll")
	public Message getAll() {
		List<countract> all;
		try {
			all = cs.getAll();
			return  MessageUtil.success(all);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return MessageUtil.error("亲，出了点问题呢");
		}
	}
	
	@PostMapping("selectByCount")
	public Message selectByCount(@RequestBody BaseUser u) {
		try {
			List<countract> countractByUser = cs.getCountractByUser(u.getCount());
			return MessageUtil.success(countractByUser);
		} catch (Exception e) {
			return MessageUtil.error(e.getMessage());
		}
	}
	
	
	public Message saveOfUpdate( @RequestBody BaseCountract c) {
		try {
			cs.saveOfUpdate(c);
			return MessageUtil.success("操作成功");
		} catch (Exception e) {
			return MessageUtil.error(e.getMessage());
		}
	}
}
