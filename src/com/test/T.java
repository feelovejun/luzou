package com.test;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sreach.spider.DownloadPage;
import com.sreach.spider.HrefOfPage;


/**
 * 
 * @author ¾üÓª http://sdlgxxy.iteye.com/blog/798576
 * 
 * http://www.iteye.com/problems/73559
 */
public class T {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url="http://news.ifeng.com/listpage/11574/0/1/rtlist.shtml";
		String content=DownloadPage.getContentFormUrl(url);
		HrefOfPage.getHrefOfContent(content);
	}
	
	
	

}
