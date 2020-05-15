package com.tjk.apps.cms.bean.extend;

import java.util.List;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.Basemenu;

public class User extends BaseUser {

	private List<Role> roles;
    private List<Menu> menus;
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [roles=" + roles + "]";
	}
	
	
}
