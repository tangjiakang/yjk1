package com.tjk.apps.cms.service;

import java.util.List;

import com.tjk.apps.cms.bean.extend.Menu;

public interface MenuService {
	List<Menu> selectByRoleId(int id);
}
