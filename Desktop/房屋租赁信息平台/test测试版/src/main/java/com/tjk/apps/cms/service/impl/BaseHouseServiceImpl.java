package com.tjk.apps.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjk.apps.cms.bean.Basehouse;
import com.tjk.apps.cms.bean.BasehouseExample;
import com.tjk.apps.cms.bean.extend.House;
import com.tjk.apps.cms.dao.BasehouseMapper;
import com.tjk.apps.cms.dao.extend.HouseExtend;
import com.tjk.apps.cms.service.BaseHouseService;
@Service
public class BaseHouseServiceImpl implements BaseHouseService {

	@Autowired
	private BasehouseMapper bhm;
	
	
	
	
	@Override
	public void saveOfUpdate(Basehouse house) throws Exception {
		BasehouseExample example = new BasehouseExample();
		if(house.gethId() == null) {
			example.or().andDetailedaddressEqualTo(house.getDetailedaddress());
			List<Basehouse> list = bhm.selectByExample(example);
			if(list.size() == 0)
				bhm.insert(house);
			else
				throw new Exception("地址已存在");
		}else {
			bhm.updateByPrimaryKey(house);
		}
	}

	@Override
	public void deleteById(int id)throws Exception {
	       bhm.deleteByPrimaryKey(id);
	}

	@Override
	public List<Basehouse> selectBybulding(String bulding) throws Exception{
		BasehouseExample example = new BasehouseExample();
		example.or().andBuldingLike(bulding);
		System.out.println(bulding);
		List<Basehouse> list = bhm.selectByExample(example);
		System.out.println(list.size());
		return list;
	}
	
	
	
	

}
