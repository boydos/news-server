package com.sdt.newsserver.worker;

import java.util.Calendar;

import com.bigknow.minero.db.sql.ResultHelper;
import com.bigknow.minero.log.MLog;
import com.bigknow.minero.model.ModelBean;
import com.bigknow.minero.util.DateUtil;
import com.bigknow.minero.util.StringUtil;
import com.bigknow.minero.web.PassModel;
import com.sdt.newsserver.utils.Global;
import com.sdt.newsserver.utils.JsonHelper;
import com.sdt.newsserver.utils.MemStore;
import com.sdt.newsserver.utils.ResultUtils;

public class NewsWorker extends BaseNewsWorker{
	MLog log= MLog.getLog(NewsWorker.class);
	public void getTest(PassModel passModel) {
		if(Global.test) {
			log.debug("Get Recommand News Success!");
			passModel.setResponseContent(JsonHelper.getJson("test"));
			return;
		}	
	}
	public void getNews(PassModel passModel) {
		String tag = passModel.getPara("tag");
		if(StringUtil.isEmpty(tag)) {
			tag = "default";
		}
		ModelBean response = ResultUtils.getSuccess("新闻获取成功");
		response.set("list", MemStore.getNews(tag));
		
		passModel.setResponseContent(response.toJson());
	}
	
	public void createNews(PassModel passModel) {
		String title = passModel.getPara("title");
		String tag = passModel.getPara("tag");
		String content = passModel.getPara("content");
		if(StringUtil.isEmpty(title)) {
			passModel.setResponseContent(ResultHelper.getError("标题不能为空"));
			return;
		}
		if(StringUtil.isEmpty(content)) {
			passModel.setResponseContent(ResultHelper.getError("内容不能为空"));
			return;
		}
		log.debug(String.format("content= %s", content));
		if(StringUtil.isEmpty(tag)) {
			tag = "default";
		}
		
		ModelBean news = new ModelBean();
		news.set("title", title);
		news.set("content", content);
		
		news.set("date",getNowDate());
		news.set("tag", tag);
		int ret =MemStore.addNews(tag, news);
		passModel.setResponseContent(ResultHelper.getSuccess(String.format("新闻添加成功-[%s==%d]", tag,ret)));
	}
	
	private String getNowDate() {
		Calendar cal= Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		return  DateUtil.date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
	}
}
