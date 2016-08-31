package com.sdt.newsserver.utils;

import java.util.UUID;

public class Global {
	public final static boolean test = true;
	
	public final static String NEWS_TAG_DEFAULT ="default";
	public final static String NEWS_TAG_RECOMMEND ="recommend";
	public final static String NEWS_TAG_BJ ="beijing";
	public final static String NEWS_TAG_SH ="shenghuo";
	public final static String NEWS_TAG_TY ="tiyu";
	public final static String NEWS_TAG_OTHER ="other";
	
	public static String getUniqueId() {
		return UUID.randomUUID().toString();
	}
}
