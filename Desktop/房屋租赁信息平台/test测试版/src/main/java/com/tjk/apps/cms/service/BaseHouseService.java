package com.tjk.apps.cms.service;

import java.util.List;

import com.tjk.apps.cms.bean.Basehouse;

public interface BaseHouseService {

	void saveOfUpdate(Basehouse house)throws Exception;
	void deleteById(int id)throws Exception;
	List<Basehouse> selectBybulding(String bulding)throws Exception;
}
