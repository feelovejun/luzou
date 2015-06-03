package com.luzou.main;



import java.util.ArrayList;
import java.util.List;

import com.luzou.bean.NewsBean;
import com.luzou.tools.Login;
import com.luzou.tools.PropertiesReader;
import com.sreach.spider.DownloadPage;
import com.sreach.spider.HrefOfPage;
import com.sreach.spider.HtmlParser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertiesReader pr=new PropertiesReader();
		String ui=pr.readValue("userinfo");		
		String[] userinfos=ui.split(",");
		
		System.out.println("============获取今日最新新闻==================");
		////////////////////////////////news///////////////////////////////////
		String url="http://news.ifeng.com/listpage/11574/0/1/rtlist.shtml";
		String content=DownloadPage.getContentFormUrl(url);
		List<String> herflist=HrefOfPage.getHrefOfContent(content);
		List<NewsBean> newslist=new ArrayList<NewsBean>();
		System.out.println("====总统拿到新闻链接数："+herflist.size());
		System.out.println("=====开始获取新闻内容=====");
		for(String nurl:herflist){
		NewsBean bn=HtmlParser.getNewsList(nurl);
		if(bn.getTitle()==null||bn.getContent()==null||bn.getTitle().equals("")||bn.getContent().equals("")){
			continue;
		}
		System.out.println("====新闻标题====="+bn.getTitle());
		newslist.add(bn);
		}
		
		/////////////////////////////////////////////////////////////
		int t=0;
		for(String user:userinfos){
		String user_email = user.split("`")[0];
		String password =  user.split("`")[1];
		System.out.println("=======("+user_email+")============");
		Login lg=new Login();
		List<NewsBean> list=new ArrayList<NewsBean>();
		for(int i=0;i<10;i++){
			if(t==newslist.size()){t=0;}
			list.add(newslist.get(t));
			t++;
		}
		lg.loginToSystem(user_email, password,list);
		}

	}

}
