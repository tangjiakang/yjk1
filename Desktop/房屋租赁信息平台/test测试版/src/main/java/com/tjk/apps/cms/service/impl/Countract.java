package com.tjk.apps.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjk.apps.cms.bean.BaseCountract;
import com.tjk.apps.cms.bean.BaseCountractExample;
import com.tjk.apps.cms.bean.BaseCountractExample.Criteria;
import com.tjk.apps.cms.bean.BaseUserExample;
import com.tjk.apps.cms.bean.extend.User;
import com.tjk.apps.cms.bean.extend.countract;
import com.tjk.apps.cms.dao.BaseCountractMapper;
import com.tjk.apps.cms.dao.BaseUserMapper;
import com.tjk.apps.cms.dao.BasehouseMapper;
import com.tjk.apps.cms.dao.extend.UserExtend;

@Service
public class Countract {
	@Autowired
	BaseCountractMapper co;
	@Autowired
	BaseUserMapper cu;
	@Autowired
	BasehouseMapper ch;
	@Autowired
	UserExtend userextend;	
	public List<countract> getCountractByUser(String count) throws Exception{
		
		User user = userextend.SelectByCount(count);
		if(user==null) {
			throw new Exception("账号不存在，请重新输入");
		}
		
		List<countract>  all =  new ArrayList<countract>();
		BaseCountractExample ex = new BaseCountractExample();
		ex.or().andCustomerEqualTo(user.getuId());
		List<BaseCountract> all1 = co.selectByExample(ex);
		for (BaseCountract s: all1) {
			countract c = new countract();
			c.setcId(s.getcId());
			c.setCommittime(s.getCommittime());
			c.setDateofhire(s.getDateofhire());
			c.setMonthlyrent(s.getMonthlyrent());
			c.setTotal(s.getTotal());
			c.setRemarks(s.getRemarks());
			c.setO(cu.selectByPrimaryKey(s.getOwer()));
			c.setC(cu.selectByPrimaryKey(s.getCustomer()));
			c.setH(ch.selectByPrimaryKey(s.getHouse()));
			all.add(c);
		}
		return all;
	}
	
	public countract selectByid(int id){
		BaseCountract s = co.selectByPrimaryKey(id);
		countract c = new countract();
		c.setcId(s.getcId());
		c.setCommittime(s.getCommittime());
		c.setDateofhire(s.getDateofhire());
		c.setMonthlyrent(s.getMonthlyrent());
		c.setTotal(s.getTotal());
		c.setRemarks(s.getRemarks());
		c.setO(cu.selectByPrimaryKey(s.getOwer()));
		c.setC(cu.selectByPrimaryKey(s.getCustomer()));
		c.setH(ch.selectByPrimaryKey(s.getHouse()));
		return c;
	}
	
	public List<countract> getAll() throws Exception{
		List<countract>  all =  new ArrayList<countract>();
		BaseCountractExample ex = new BaseCountractExample();
		ex.or();
		List<BaseCountract> all1 = co.selectByExample(ex);
		for (BaseCountract s: all1) {
			countract c = new countract();
			c.setcId(s.getcId());
			c.setCommittime(s.getCommittime());
			c.setDateofhire(s.getDateofhire());
			c.setNorentdate(s.getNorentdate());
			c.setMonthlyrent(s.getMonthlyrent());
			c.setTotal(s.getTotal());
			c.setRemarks(s.getRemarks());
			c.setO(cu.selectByPrimaryKey(s.getOwer()));
			c.setC(cu.selectByPrimaryKey(s.getCustomer()));
			c.setH(ch.selectByPrimaryKey(s.getHouse()));
			all.add(c);
		}
		return all;
	}
	
	public void saveOfUpdate(BaseCountract baseCountract) throws Exception {
		BaseCountractExample ex = new BaseCountractExample();
		 ex.or().andCIdEqualTo(baseCountract.getcId());
		 List<BaseCountract> list = co.selectByExample(ex);
		 if(list != null && list.size()!=0) {
			 co.updateByPrimaryKey(baseCountract);
		 }else {			 
			 co.insert(baseCountract);
		 }
	}
     
}
