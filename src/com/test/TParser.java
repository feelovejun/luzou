package com.test;

import com.luzou.bean.NewsBean;
import com.sreach.spider.HtmlParser;

public class TParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://news.ifeng.com/a/20150519/43785995_0.shtml";
		NewsBean bn=HtmlParser.getNewsList(url);
		System.out.println(bn.getTitle().trim());
		System.out.println(bn.getContent().trim());

	}

}
