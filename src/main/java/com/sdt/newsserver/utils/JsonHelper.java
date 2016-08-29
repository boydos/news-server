package com.sdt.newsserver.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.bigknow.minero.log.MLog;
import com.bigknow.minero.util.StreamUtil;
import com.sdt.newsserver.InitNewsServer;

public class JsonHelper {
	private static MLog log = MLog.getLog(JsonHelper.class);
	
	public static String getJson(String jsonkey) {
		String jsonDir =InitNewsServer.getJsonDir();
		String fileName =jsonDir +jsonkey +".json";
		FileInputStream fsin = null;
		try {
			fsin =new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("Cann't find json file : "+fileName);
			e.printStackTrace();
			return "";
		}
		return StreamUtil.inStreamToString(fsin);
	}
}
