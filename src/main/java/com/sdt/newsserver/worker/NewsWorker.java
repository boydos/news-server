package com.sdt.newsserver.worker;

import com.bigknow.minero.log.MLog;
import com.bigknow.minero.web.PassModel;
import com.sdt.newsserver.utils.Global;
import com.sdt.newsserver.utils.JsonHelper;

public class NewsWorker extends BaseNewsWorker{
	MLog log= MLog.getLog(NewsWorker.class);
	public void getRecommand(PassModel passModel) {
		if(Global.test) {
			log.debug("Get Recommand News Success!");
			passModel.setResponseContent(JsonHelper.getJson("test"));
			return;
		}
		
	}
}
