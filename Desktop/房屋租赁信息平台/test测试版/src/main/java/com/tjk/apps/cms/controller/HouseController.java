package com.tjk.apps.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tjk.apps.cms.bean.Basehouse;
import com.tjk.apps.cms.bean.extend.House;
import com.tjk.apps.cms.comment.Invocation;
import com.tjk.apps.cms.comment.Message;
import com.tjk.apps.cms.comment.MessageUtil;
import com.tjk.apps.cms.dao.extend.HouseExtend;
import com.tjk.apps.cms.service.impl.BaseHouseServiceImpl;

@RequestMapping("/house")
@RestController
public class HouseController {
   
	@Autowired
	private BaseHouseServiceImpl bhsi ;
	@Autowired
	private  HouseExtend he;
	
	@PostMapping("SaveOfupdate")
	public Message saveOfUpdate(@RequestBody Basehouse house)  {
		try {
			bhsi.saveOfUpdate(house);
			return MessageUtil.success("修改成功");
		} catch (Exception e) {
			return MessageUtil.error("修改失败");
		}
	}
	
	@PostMapping("deleteByid")
	public Message deleteById(@RequestBody  Basehouse house)  {
		System.out.println(house.gethId());
		try {
			bhsi.deleteById(house.gethId());
			return MessageUtil.success("删除成功");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return MessageUtil.error("删除失败");
		}
	}
	@PostMapping("selectByBulding")
	public List<Basehouse> selectByBulding(String bulding)throws Exception{
		List<Basehouse> list = bhsi.selectBybulding(bulding);
		return list;
	}
	@Invocation("house:view")
	@PostMapping("selectByAll")
	public List<House> selectByAll() throws Exception{
		return he.selectByAll();
	}
	@Invocation("house:view")
	@PostMapping("selectByCombination")
	public List<House> selectByCombination(@RequestBody  Basehouse house)throws Exception{
		System.out.println(house.getDetailedaddress());
		return he.selectByCombination(house.getBulding(), house.getState(), house.getDetailedaddress());
	}
}
