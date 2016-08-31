package com.sdt.newsserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.sdt.newsserver.InitNewsServer;
import com.sdt.newsserver.utils.MemStore;

public class InitNewsServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7278525931583700171L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		InitNewsServer.init();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		MemStore.flushToDisk();
	}
	
}
