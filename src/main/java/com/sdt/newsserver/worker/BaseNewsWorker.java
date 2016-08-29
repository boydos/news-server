package com.sdt.newsserver.worker;

import com.bigknow.minero.web.BaseWorker;
import com.bigknow.minero.web.PassModel;
import com.sdt.newsserver.utils.Global;

public class BaseNewsWorker extends BaseWorker{

	@Override
	protected boolean beforeProcess(PassModel passModel) {
		// TODO Auto-generated method stub
		if(Global.test) {
			return true;
		}
		return super.beforeProcess(passModel);
	}

}
