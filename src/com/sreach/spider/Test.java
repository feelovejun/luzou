package com.sreach.spider;
import java.sql.SQLException;  
     
import com.sreach.spider.UrlDataHanding;  
import com.sreach.spider.UrlQueue;  
     
public class Test  
{  
  public static void main(String[] args) throws SQLException  
  {  
	  String url="http://news.ifeng.com/listpage/11574/0/1/rtlist.shtml";
    
           
      UrlQueue.addElem(url);  
     
      UrlDataHanding[] url_Handings = new UrlDataHanding[10];  
           
          for(int i = 0 ; i < 10 ; i++)  
          {  
              url_Handings[i] = new UrlDataHanding();  
              new Thread(url_Handings[i]).start();  
          }  
     
  }  
}