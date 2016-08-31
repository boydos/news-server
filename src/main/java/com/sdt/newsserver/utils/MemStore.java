package com.sdt.newsserver.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bigknow.minero.log.MLog;
import com.bigknow.minero.model.ModelBean;
import com.bigknow.minero.util.StringUtil;
import com.sdt.newsserver.InitNewsServer;

public class MemStore {
	private static ModelBean mem =new ModelBean();
	private static MLog log = MLog.getLog(MemStore.class);
	private static final String KEY_OF_NEWS_TAG ="key_of_news_tag_value_tds";
	private static final String KEY_OF_NEWS_LIST="key_of_news_list_tds";
	private static final String STORE_PATH=InitNewsServer.getFileDir()+"_mem_store_.bin";
	public static List<ModelBean> getMem(String tag) {
		List<ModelBean> news= mem.getList(KEY_OF_NEWS_LIST);
		if(news ==null) {
			news = new ArrayList<ModelBean>();
			mem.set(tag, news);
		}
		return news;
	}
	public static List<ModelBean> getNewsList() {
		return getMem(KEY_OF_NEWS_LIST);
	}
	public static String addNews(ModelBean news) {
		if(isEmpty(news)) return null;
		List<ModelBean> list = getNewsList();
		list.add(news);
		String id =Global.getUniqueId();
		news.set("_id", id);
		return id;
	}
	public static ModelBean getNewsById(String id) {
		List<ModelBean> list =getNewsList();
		if(StringUtil.isEmpty(id)) return new ModelBean();
		for(ModelBean news : list) {
			String _id = news.getString("_id");
			if(_id!=null && _id.equals(id)) {
				return news;
			}
		}
		return new ModelBean();
	}
	public static List<ModelBean> getNewsByTag(String tag) {
		List<ModelBean> list =getNewsList();
		List<ModelBean> result = new ArrayList<ModelBean>();
		if(!StringUtil.isEmpty(tag)) {
			tag =tag.trim();
			int size =list.size();
			for(int i=size-1;i>=0;i--) {
				ModelBean news = list.get(i);
				String tempTag=news.getString("tag");
				if(tempTag!=null&&(tag.equalsIgnoreCase(tempTag)|| tempTag.toLowerCase().contains(tag.toLowerCase()+",")||tempTag.toLowerCase().contains(","+tag.toLowerCase()))) {
					result.add(news);
				}
			}
		} 
		return result;
	}
	
	public static List<ModelBean> getNewsTag() {
		return getMem(KEY_OF_NEWS_TAG);
	}
	public static String addTag(ModelBean tag) {
		if(isEmpty(tag)) return null;
		List<ModelBean> list = getNewsTag();
		list.add(tag);
		String id =Global.getUniqueId();
		tag.set("_id", id);
		return id;
	}

	public static boolean isEmpty(List<ModelBean> list) {
		return list==null ||list.size() ==0;
	}
	public static boolean isEmpty(ModelBean model) {
		return model==null || model.keySet() ==null ||model.keySet().isEmpty();
	}
	
	public static void flushToDisk() {
		log.debug("flush to disk");
		writeFile(STORE_PATH,mem.toJson());
	}
	
	public static void readFromDisk() {
		log.debug("read from disk");
		String jsonString =readFile(STORE_PATH);
		if(!StringUtil.isEmpty(jsonString)) {
			mem = new ModelBean(jsonString);
		}
	}
	
	public static void writeFile(String path, String content) { 
		File file = new File(path);
		BufferedWriter writer=null;
        try {
	        if (file.exists()) {  
			  file.delete();
	        }  
	        file.createNewFile();
	        writer=new BufferedWriter(new FileWriter(file));  
	        writer.write(content);  
	        writer.flush(); 	        
		} catch (IOException e) {
			e.printStackTrace();
		}
        finally  {
        	if(writer!=null)
				try {
					writer.close();
				} catch (IOException e) {}
        }
	}
	
	public static String readFile(String path) {
		File file = new File(path);
		if(!file.exists()) return null;
		BufferedReader reader=null;
		String str= "";
		try {
			 reader = new BufferedReader(new FileReader(file));
			 String line =reader.readLine();
			 while(line !=null) {
				 str+=line;
				 line =reader.readLine();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(reader !=null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		log.debug(str);
		return str;
	}
}
