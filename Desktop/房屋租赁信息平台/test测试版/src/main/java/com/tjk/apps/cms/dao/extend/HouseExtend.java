package com.tjk.apps.cms.dao.extend;
import java.util.List;

import com.tjk.apps.cms.bean.extend.House;

public interface HouseExtend {
	
	List<House> selectByAll() throws Exception;
	
	List<House> selectByCombination(String bulding,String state,String DetailedAddress)throws Exception;

}
