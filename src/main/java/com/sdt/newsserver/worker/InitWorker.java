package com.sdt.newsserver.worker;

import com.bigknow.minero.model.ModelBean;
import com.bigknow.minero.web.PassModel;
import com.sdt.newsserver.utils.Global;

public class InitWorker extends BaseNewsWorker{
	public void getSystemMenu(PassModel passModel) {
		ModelBean result = new ModelBean();
		
		if(Global.test) {
			result.set("s", 1);
			result.set("i", "执行成功!");
			result.set("menuid", "01,02,03");
		} else {
			result.set("s", 0);
			result.set("i", "执行失败!");
		}
		passModel.setResponseContent(result.toJson());
	}
}
