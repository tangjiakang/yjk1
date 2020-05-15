package com.tjk.apps.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjk.apps.cms.bean.extend.Menu;
import com.tjk.apps.cms.service.MenuService;

@RequestMapping("/Menu")
@RestController
public class MenuController {

	@Autowired
	MenuService ms;
	
	@PostMapping("getMenu")
	List<Menu> getMenu(int id){
		List<Menu> list = ms.selectByRoleId(id);
		return list;
	}
}
