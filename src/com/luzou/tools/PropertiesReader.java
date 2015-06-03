package com.luzou.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;



public class PropertiesReader {
	//String url="";
	 String url=System.getProperty("user.dir").replace("%20", " ")+"\\";
	
	// String url=Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1).replace("%20", " ");
public  String readValue(String key) {
	   Properties props = new Properties();
	   System.out.println(url);
	   File file = new File(url+"info.properties");  
	         try {
	        	 
	        InputStream in=new FileInputStream(file);
	          props.load(in);
	          String value = props.getProperty (key);
	             return value;
	         } catch (Exception e) {
	          e.printStackTrace();
	          return null;
	         }
	  }
	  
	     @SuppressWarnings("unchecked")
		public   void readProperties() {
	      Properties props = new Properties();
	      System.out.println(url);
	      File file = new File(url+"info.properties");  
	         try {
	          InputStream in = new FileInputStream(file);
	          props.load(in);
	             Enumeration en = props.propertyNames();
	              while (en.hasMoreElements()) {
	               String key = (String) en.nextElement();
	                     String Property = props.getProperty (key);
	                     System.out.println(key+"="+Property);
	                 }
	         } catch (Exception e) {
	          e.printStackTrace();
	         }
	     }
	 
	     public   void writeData(String key, String value) {  
	         Properties prop = new Properties();  
	         InputStream fis = null;  
	         OutputStream fos = null;  
	         try {  
	              
	             File file = new File(url+"info.properties");  
	             
	             if (!file.exists()) { 
	                 file.createNewFile();
	                 }
	             fis = new FileInputStream(file);  
	             prop.load(fis);  
	             fis.close();  
	             fos = new FileOutputStream(file);  
	             prop.setProperty(key, value);  
	             prop.store(fos, "zst");
	             fos.close();  
	               
	         } catch (IOException e) {  
	        	 
	             System.err.println("Visit  for updating "+ value + " value error");  
	         }
	         
	     }   

}
