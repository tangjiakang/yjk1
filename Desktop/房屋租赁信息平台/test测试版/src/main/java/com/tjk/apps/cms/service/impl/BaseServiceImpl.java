package com.tjk.apps.cms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjk.apps.cms.bean.BaseRoleMenuExample;
import com.tjk.apps.cms.bean.BaseRoleMenuKey;
import com.tjk.apps.cms.bean.BaseUserRoleExample;
import com.tjk.apps.cms.bean.extend.Menu;
import com.tjk.apps.cms.dao.BaseRoleMenuMapper;
import com.tjk.apps.cms.dao.BasemenuMapper;
import com.tjk.apps.cms.dao.extend.BaseMenu;
import com.tjk.apps.cms.service.MenuService;
@Service
public class BaseServiceImpl implements MenuService{
	@Autowired
	BaseRoleMenuMapper rm;
    @Autowired
	BaseMenu baseMenu;
    @Autowired
    BasemenuMapper baseMenuMapper;
	
	@Override
	public List<Menu> selectByRoleId(int id) {
		BaseRoleMenuExample example = new BaseRoleMenuExample();
		example.createCriteria().andRIdEqualTo(id);
		List<Menu> menus = new ArrayList<>();
	    List<Menu> all = baseMenu.getAll();
	    List<BaseRoleMenuKey> temp = rm.selectByExample(example);
	    List<Integer> ms = new ArrayList<>();
	    Map<Integer, Menu> map = new HashMap<>();
	    for (BaseRoleMenuKey m : temp) {
			ms.add(m.getmId());
		}
	    for (Menu m : all) {
			if(ms.contains(m.getmId())) {
				m.setChecked(true);
			}
			map.put(m.getmId(), m);
		}
	    for (Menu m : all) {
			Menu child = m;
			if(child.getPid() == 0) {
				menus.add(m);
			}else {
				Menu parent = map.get(child.getPid());
				parent.getChildren().add(child);
			}
		}
		return menus;
	}

}
