package com.tjk.apps.cms.service;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.extend.User;
import com.tjk.apps.cms.dao.extend.UserVM;


public interface BaseUserService {
	 User login(UserVM userVM) throws Exception ;
	void SaveOrUpdate(BaseUser baseUser) throws Exception;
	
}
