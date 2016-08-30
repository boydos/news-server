package com.sdt.newsserver.utils;

import java.util.ArrayList;
import java.util.List;

import com.bigknow.minero.model.ModelBean;

public class MemStore {
	private static ModelBean mem =new ModelBean();
	private static final String KEY_OF_NEWS_TAG ="key_of_news_tag_value_tds";
	
	public static List<ModelBean> getNews(String tag) {
		List<ModelBean> news= mem.getList(tag);
		if(news ==null) {
			news = new ArrayList<ModelBean>();
			mem.set(tag, news);
		}
		return news;
	}
	
	public static int addNews(String tag,ModelBean news) {
		if(isEmpty(news)) return -1;
		List<ModelBean> list = getNews(tag);
		list.add(news);
		int id =list.size();
		news.set("_id", id);
		return id;
	}
	
	public static List<ModelBean> getNewsTag() {
		return getNews(KEY_OF_NEWS_TAG);
	}
	public static void addTag(ModelBean model) {
		addNews(KEY_OF_NEWS_TAG, model);
	}

	public static boolean isEmpty(List<ModelBean> list) {
		return list==null ||list.size() ==0;
	}
	public static boolean isEmpty(ModelBean model) {
		return model==null || model.keySet() ==null ||model.keySet().isEmpty();
	}
}
