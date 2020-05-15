package com.tjk.apps.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tjk.apps.cms.bean.extend.House;
import com.tjk.apps.cms.dao.extend.HouseExtend;

public class HouseextendImpl implements HouseExtend{

	@Autowired
	private HouseExtend houseExtend;
	
	@Override
	public List<House> selectByAll() throws Exception{
		
		return houseExtend.selectByAll();
	}

	@Override
	public List<House> selectByCombination(String bulding, String state, String DetailedAddress)throws Exception {
		// TODO Auto-generated method stub
		return houseExtend.selectByCombination(bulding, state, DetailedAddress);
	}

}
