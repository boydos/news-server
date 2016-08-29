package com.sdt.newsserver;

import java.util.Locale;
import java.util.ResourceBundle;

import com.bigknow.minero.log.MLog;
import com.bigknow.minero.log.MLogFactory;

public class InitNewsServer {
	private static ResourceBundle serverConfig = null;
    private static ResourceBundle configure = null;
    private static String baseDir = null;
    static {
        serverConfig = ResourceBundle.getBundle("newsserver", Locale.getDefault());
        baseDir = serverConfig.getString("newsserver.basedir");
        configure = ResourceBundle.getBundle("config", Locale.getDefault());
    }
    public static void init() {
    	MLog.setLogFactory(new MLogFactory(){

			@Override
			public String getConfigFilePath() {
				return baseDir + "/conf/log4j.properties";
			}

			@Override
			public String getLogFile() {
				return baseDir + "/logs/newsserver.out";
			}
    		
    	});
    	 MLog log = MLog.getLog(InitNewsServer.class);
    	 log.info("Init NewsServer Project Done! baseDir="+baseDir);
    }
    public static String getBaseDir() {
        return baseDir;
    }

    public static String getJsonDir() {
        return baseDir + "/json/";
    }
    public static int getConfigInt ( String key ) {
    	return getConifgInt(key, 0);
    }
    public static String getConfigString ( String key ) {
    	return getConfigString(key, "");
    }
    public static int getConifgInt ( String key ,int def) {
    	String value = configure.getString(key);
    	Integer i = def;
    	try {
    		i = Integer.parseInt(value);
    	} catch (Exception e) {}
    	return i;
    }
    public static String getConfigString( String key ,String def) {
    	String value = configure.getString(key);
    	if ( value == null) return def;
    	else return  value;
    }
    public static Object getConfigObj( String key) {
    	return configure.getObject(key);
    }
}
