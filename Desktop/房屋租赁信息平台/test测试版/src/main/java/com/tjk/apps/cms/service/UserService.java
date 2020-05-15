package com.tjk.apps.cms.service;

import java.util.List;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.extend.User;

public interface UserService {
	
	public void saveOrUpdate(BaseUser baseUser) throws Exception;
	void deleteUser(int  id)throws Exception;
	List<BaseUser> selectByNameOrAge(String name);
	List<User>selectByCombination(String name,String gender,String adress) throws Exception;
}
