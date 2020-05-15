package com.tjk.apps.cms.dao.extend;

import java.util.List;

import com.tjk.apps.cms.bean.BaseUser;
import com.tjk.apps.cms.bean.extend.User;

public interface UserExtend {
  User SelectByCount(String count);
  void saveOfUpdate(BaseUser u);
  List<User>selectByCombination(String name,String phone,String address);
}
