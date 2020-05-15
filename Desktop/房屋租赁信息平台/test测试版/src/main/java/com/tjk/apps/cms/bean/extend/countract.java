package com.tjk.apps.cms.bean.extend;

import com.tjk.apps.cms.bean.BaseCountract;
import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.Basehouse;

public class countract extends BaseCountract  {
    
	BaseUser c,o ;
	Basehouse h;
	public BaseUser getC() {
		return c;
	}
	public void setC(BaseUser c) {
		this.c = c;
	}
	public BaseUser getO() {
		return o;
	}
	public void setO(BaseUser o) {
		this.o = o;
	}
	public Basehouse getH() {
		return h;
	}
	public void setH(Basehouse h) {
		this.h = h;
	}
	public countract(BaseUser c, BaseUser o, Basehouse h) {
		super();
		this.c = c;
		this.o = o;
		this.h = h;
	}
	public countract() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
