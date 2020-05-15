package com.tjk.apps.cms.service.impl;

import java.util.List;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.BaseUserExample;
import com.tjk.apps.cms.bean.BaseUserExample.Criteria;
import com.tjk.apps.cms.bean.BaseUserRoleKey;
import com.tjk.apps.cms.bean.extend.User;
import com.tjk.apps.cms.dao.BaseUserMapper;
import com.tjk.apps.cms.dao.BaseUserRoleMapper;
import com.tjk.apps.cms.dao.extend.UserExtend;
import com.tjk.apps.cms.service.UserService;
@Service
public class UserServiceImpl implements UserService {
     @Autowired
	BaseUserMapper bum;
     @Autowired
     BaseUserRoleMapper ur;
     @Autowired
     UserExtend u;   
	
	@Override
	public void saveOrUpdate(BaseUser baseUser) throws Exception {
		if(baseUser.getuId() == null) {
			if(u.SelectByCount(baseUser.getCount()) == null) {
				bum.insert(baseUser); 
				ur.insert(new BaseUserRoleKey(baseUser.getuId(),1));
			}else {
				throw new Exception("账号已存在请换账号");
			}
		}else {
			bum.updateByPrimaryKey(baseUser);
		}
	}

	@Override
	public void deleteUser(int  id) throws Exception {
		if(bum.selectByPrimaryKey(id)== null){
			throw new Exception("需要删除的用户不存在");
		}else {
			bum.deleteByPrimaryKey(id);
		}
		
	}

	@Override
	public List<BaseUser> selectByNameOrAge(String name) {
		BaseUserExample example = new BaseUserExample();
		 example.or().andNameLike(name);
		List<BaseUser> BaseUsers = bum.selectByExample(example);
		return BaseUsers;
	}

	@Override
	public List<User> selectByCombination(String name, String phone, String adress) throws Exception{
		return u.selectByCombination(name, phone, adress);
	}
	
	public List<BaseUser> getAll() throws Exception{
		BaseUserExample example = new BaseUserExample();
		List<BaseUser> BaseUsers = bum.selectByExample(example);
		return BaseUsers;
	}

}
