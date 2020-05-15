package com.tjk.apps.cms.bean.extend;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.tjk.apps.cms.bean.Basemenu;
@Component
public class Menu extends Basemenu{
	private Boolean checked = false;  // 回显是否被选中
    private List<Menu> children = Lists.newArrayList();  // 子集
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
    
}
