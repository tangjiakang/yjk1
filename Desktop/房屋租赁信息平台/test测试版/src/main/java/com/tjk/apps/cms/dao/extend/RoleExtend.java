package com.tjk.apps.cms.dao.extend;

import java.util.List;

import com.tjk.apps.cms.bean.extend.Role;

public interface RoleExtend {
	List<Role> selectByUserId(int id);
	
}
