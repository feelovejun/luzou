package com.sreach.spider;  

import java.util.ArrayList;
import java.util.List;
     
public class HrefOfPage  
{  
    /**  
     * 获得页面源代码中超链接  
     */
    public static List<String> getHrefOfContent(String content)  
    {  
        System.out.println("开始");  
        List<String> slist=new ArrayList<String>();
        String[] contents = content.split("<a href=\"");  
        for (int i = 1; i < contents.length; i++)  
        {  
            int endHref = contents[i].indexOf("\"");  
     
            String aHref = FunctionUtils.getHrefOfInOut(contents[i].substring(  
                    0, endHref));  
     
            if (aHref != null)  
            {  
                String href = FunctionUtils.getHrefOfInOut(aHref);  
     
                if (!UrlQueue.isContains(href) && href.contains("news.ifeng.com/a/"))  
                {  
                	slist.add(href);
                    
                }  
            }  
        }  
     
     return slist;
     
    }  
     
}