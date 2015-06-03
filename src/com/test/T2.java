package com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.luzou.tools.SendRequest;


public class T2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String encoding = "UTF-8";
		String url = "http://www.luzou.cn/account/login/submit";
		String referer = "http://www.luzou.cn/";
		String user_email = "feelovejun@163.com";
		String password = "junying277";
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("referer",referer);
		parameters.put("user_email",user_email);
		parameters.put("user_pass",password);
		
		try {
			String str = SendRequest.sendPost(url, null, parameters,encoding).getCookie();
			System.out.println(str);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
