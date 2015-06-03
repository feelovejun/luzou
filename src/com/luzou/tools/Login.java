package com.luzou.tools;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;

import com.luzou.bean.NewsBean;

public class Login{

	
	
	public void loginToSystem(String user_email,String password,List<NewsBean> nlist)
	{
		
		String encoding = "UTF-8";
		String url = "http://www.luzou.cn/account/login/submit";
		String referer = "http://www.luzou.cn/";
		HttpClient httpclient = new DefaultHttpClient();
		
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity uefEntity;
		HttpResponse response;
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("referer",referer));			
		formparams.add(new BasicNameValuePair("user_email",user_email));
		formparams.add(new BasicNameValuePair("user_pass",password));
		try{
			uefEntity = new UrlEncodedFormEntity(formparams,encoding);
			httppost.setEntity(uefEntity);
			response = httpclient.execute(httppost);
		
	          
	 	    System.out.println("======================登陆处理===============================");
	 	  if(response.getStatusLine().getStatusCode()==302){
	        System.out.println(response.getStatusLine().getStatusCode());  
	        
	        CookieStore cookies = ((AbstractHttpClient) httpclient).getCookieStore();    
	        httppost.abort();
	        
	        ((AbstractHttpClient) httpclient).setCookieStore(cookies);
	        HttpGet httpget2 = new HttpGet("http://www.luzou.cn/member/lucky");  
	        response=httpclient.execute(httpget2); 
	        httpget2.abort();
	        
	        System.out.println("=======================抽奖处理==============================");
	        System.out.println(response.getProtocolVersion());  
	        System.out.println(response.getStatusLine().getStatusCode());  
	        System.out.println(response.getStatusLine().getReasonPhrase());  
	        System.out.println(response.getStatusLine().toString());  
	       
	       for(NewsBean nb:nlist ){
	      this.postForm(cookies, "17", nb.getTitle(), nb.getContent(), encoding);
	      try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       }
	 	  }else{
	 		  System.out.println("===用户登录失败======");
	 	  }
	      httpclient.getConnectionManager().shutdown();
		}catch (ClientProtocolException e){
			e.printStackTrace();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void postForm(CookieStore cookieStore,String cat_id,String post_title, String post_content,String encoding){
		
		try {
			HttpClient httpclient = new  DefaultHttpClient(new ThreadSafeClientConnManager());
			((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
			System.out.println("==========================发帖处理===========================");
			HttpPost httppost = new HttpPost("http://www.luzou.cn/post/publish");
			UrlEncodedFormEntity uefEntity;
			HttpResponse response;
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("cat_id",post_content));			
			formparams.add(new BasicNameValuePair("post_content",post_content));
			formparams.add(new BasicNameValuePair("post_title",post_title));
			uefEntity = new UrlEncodedFormEntity(formparams,encoding);
			httppost.setEntity(uefEntity);
			System.out.println("========1=========");
			response = httpclient.execute(httppost);
			System.out.println("========2=========");
			httppost.abort();
	        System.out.println(response.getProtocolVersion());  
	        System.out.println(response.getStatusLine().getStatusCode());  
	        System.out.println(response.getStatusLine().getReasonPhrase());  
	        System.out.println(response.getStatusLine().toString()); 
	        System.out.println("==========================处理完毕===========================");
	        httpclient.getConnectionManager().shutdown();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

