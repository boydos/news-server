package com.sdt.newsserver.utils;

import com.bigknow.minero.model.ModelBean;

public class ResultUtils {
	public static ModelBean getSuccess( String info ) {
		ModelBean model = new ModelBean ();
		model.set("s", 1);
		model.set("i", info);
		return model ;
	}
	public static ModelBean getError( String info ) {
		ModelBean model = new ModelBean ();
		model.set("s", 0);
		model.set("i", info);
		return model ;
	}
}
