package com.tjk.apps.cms.dao.extend;

import java.util.List;
import com.tjk.apps.cms.bean.extend.Menu;

public interface BaseMenu {
	List<Menu> selectByRoleId(int id);
	List<Menu> getAll();
}
