package com.tjk.apps.cms.comment;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.tjk.apps.cms.bean.BaseRoleMenuKey;
@Component
public class MenuVO {
	    private String roleId;
	    List<BaseRoleMenuKey> rolePermissionList = Lists.newArrayList();
	
}
