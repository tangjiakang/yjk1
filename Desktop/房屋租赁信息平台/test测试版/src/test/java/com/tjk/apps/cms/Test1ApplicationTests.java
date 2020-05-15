package com.tjk.apps.cms;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.Basehouse;
import com.tjk.apps.cms.bean.extend.House;
import com.tjk.apps.cms.bean.extend.Menu;
import com.tjk.apps.cms.bean.extend.User;
import com.tjk.apps.cms.bean.extend.countract;
import com.tjk.apps.cms.comment.ReadExcle;
import com.tjk.apps.cms.config.AuthenticationInterceptor;
import com.tjk.apps.cms.dao.extend.ConstractExtend;
import com.tjk.apps.cms.dao.extend.HouseExtend;
import com.tjk.apps.cms.dao.extend.UserVM;
import com.tjk.apps.cms.service.MenuService;
import com.tjk.apps.cms.service.impl.BaseUserSerciceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1ApplicationTests {
	@Test
	public void contextLoads() throws Exception {
		String path="D:/housedata/test.xlsx";
		List<Basehouse> l = ReadExcle.readExcel(path);
		Basehouse basehouse = l.get(0);
		System.out.println(basehouse.getBulding()+basehouse.getArea()+basehouse.getDetailedaddress()+basehouse.getOrientation()+basehouse.getState());
	}
	

}
