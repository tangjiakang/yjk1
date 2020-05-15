package com.tjk.apps.cms.bean.extend;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.Basehouse;

public class House extends Basehouse{

	private BaseUser user;

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}
	
}
