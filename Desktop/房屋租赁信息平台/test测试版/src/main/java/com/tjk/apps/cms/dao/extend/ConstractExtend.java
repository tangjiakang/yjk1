package com.tjk.apps.cms.dao.extend;

import java.util.List;

import com.tjk.apps.cms.bean.extend.countract;

public interface ConstractExtend {
     List<countract>  selectByCombination(int ower,int coutomer,int id);
     List<countract>  getAll();
}
