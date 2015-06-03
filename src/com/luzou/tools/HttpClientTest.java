package com.luzou.tools;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.UnsupportedEncodingException; 
import java.security.KeyManagementException; 
import java.security.KeyStore; 
import java.security.KeyStoreException; 
import java.security.NoSuchAlgorithmException; 
import java.security.UnrecoverableKeyException; 
import java.security.cert.CertificateException; 
import java.util.ArrayList; 
import java.util.List; 
   
import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.NameValuePair; 
import org.apache.http.ParseException; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.HttpClient; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.conn.scheme.Scheme; 
import org.apache.http.conn.ssl.SSLSocketFactory; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.message.BasicNameValuePair; 
import org.apache.http.util.EntityUtils; 
   
public class HttpClientTest { 
   
    public void jUnitTest(){ 
        get(); 
    } 
       
    /**
     * HttpClient����SSL
     */ 
    private void ssl() { 
        DefaultHttpClient httpclient = new DefaultHttpClient(); 
        try { 
            KeyStore trustStore = KeyStore.getInstance(KeyStore 
                    .getDefaultType()); 
            FileInputStream instream = new FileInputStream(new File( 
                    "d:\\tomcat.keystore")); 
            try { 
                // ����keyStore d:\\tomcat.keystore 
                trustStore.load(instream, "123456".toCharArray()); 
            } catch (CertificateException e) { 
                e.printStackTrace(); 
            } finally { 
                try { 
                    instream.close(); 
                } catch (Exception ignore) { 
                } 
            } 
            // ����Socket����,��trustStoreע�� 
            SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore); 
            // ����Scheme 
            Scheme sch = new Scheme("https", 8443, socketFactory); 
            // ע��Scheme 
            httpclient.getConnectionManager().getSchemeRegistry().register(sch); 
            // ����http����(get��ʽ) 
            HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action"); 
            System.out.println("executing request" + httpget.getRequestLine()); 
            HttpResponse response = httpclient.execute(httpget); 
            HttpEntity entity = response.getEntity(); 
            System.out.println("----------------------------------------"); 
            System.out.println(response.getStatusLine()); 
            if (entity != null) { 
                System.out.println("Response content length: " 
                        + entity.getContentLength()); 
                String ss = EntityUtils.toString(entity); 
                System.out.println(ss); 
                EntityUtils.consume(entity); 
            } 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } catch (KeyManagementException e) { 
            e.printStackTrace(); 
        } catch (UnrecoverableKeyException e) { 
            e.printStackTrace(); 
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
        } catch (KeyStoreException e) { 
            e.printStackTrace(); 
        } finally { 
            httpclient.getConnectionManager().shutdown(); 
        } 
    } 
   
    /**
     * post��ʽ�ύ����ģ���û���¼����
     */ 
    private void postForm() { 
        // ����Ĭ�ϵ�httpClientʵ��. 
        HttpClient httpclient = new DefaultHttpClient(); 
        // ����httppost 
        HttpPost httppost = new HttpPost( 
                "http://localhost:8080/myDemo/Ajax/serivceJ.action"); 
        // ������������ 
        List<NameValuePair> formparams = new ArrayList<NameValuePair>(); 
        formparams.add(new BasicNameValuePair("username", "admin")); 
        formparams.add(new BasicNameValuePair("password", "123456")); 
        UrlEncodedFormEntity uefEntity; 
        try { 
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8"); 
            httppost.setEntity(uefEntity); 
            System.out.println("executing request " + httppost.getURI()); 
            HttpResponse response; 
            response = httpclient.execute(httppost); 
            HttpEntity entity = response.getEntity(); 
            if (entity != null) { 
                System.out.println("--------------------------------------"); 
                System.out.println("Response content: " 
                        + EntityUtils.toString(entity, "UTF-8")); 
                System.out.println("--------------------------------------"); 
            } 
        } catch (ClientProtocolException e) { 
            e.printStackTrace(); 
        } catch (UnsupportedEncodingException e1) { 
            e1.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            // �ر�����,�ͷ���Դ 
            httpclient.getConnectionManager().shutdown(); 
        } 
    } 
   
    /**
     * ���� post������ʱ���Ӧ�ò����ݴ��ݲ�����ͬ���ز�ͬ���
     */ 
    private void post() { 
        // ����Ĭ�ϵ�httpClientʵ��. 
        HttpClient httpclient = new DefaultHttpClient(); 
        // ����httppost 
        HttpPost httppost = new HttpPost( 
                "http://localhost:8080/myDemo/Ajax/serivceJ.action"); 
        // ������������ 
        List<NameValuePair> formparams = new ArrayList<NameValuePair>(); 
        formparams.add(new BasicNameValuePair("type", "house")); 
        UrlEncodedFormEntity uefEntity; 
        try { 
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8"); 
            httppost.setEntity(uefEntity); 
            System.out.println("executing request " + httppost.getURI()); 
            HttpResponse response; 
            response = httpclient.execute(httppost); 
            HttpEntity entity = response.getEntity(); 
            if (entity != null) { 
                System.out.println("--------------------------------------"); 
                System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8")); 
                System.out.println("--------------------------------------"); 
            } 
        } catch (ClientProtocolException e) { 
            e.printStackTrace(); 
        } catch (UnsupportedEncodingException e1) { 
            e1.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            // �ر�����,�ͷ���Դ 
            httpclient.getConnectionManager().shutdown(); 
        } 
    } 
   
    /**
     * ���� get����
     */ 
    private void get() { 
   
        HttpClient httpclient = new DefaultHttpClient(); 
   
        try { 
            // ����httpget. 
            HttpGet httpget = new HttpGet("http://www.baidu.com/"); 
            System.out.println("executing request " + httpget.getURI()); 
            // ִ��get����. 
            HttpResponse response = httpclient.execute(httpget); 
            // ��ȡ��Ӧʵ�� 
            HttpEntity entity = response.getEntity(); 
            System.out.println("--------------------------------------"); 
            // ��ӡ��Ӧ״̬ 
            System.out.println(response.getStatusLine()); 
            if (entity != null) { 
                // ��ӡ��Ӧ���ݳ��� 
                System.out.println("Response content length: " 
                        + entity.getContentLength()); 
                // ��ӡ��Ӧ���� 
                System.out.println("Response content: " 
                        + EntityUtils.toString(entity)); 
            } 
            System.out.println("------------------------------------"); 
        } catch (ClientProtocolException e) { 
            e.printStackTrace(); 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            // �ر�����,�ͷ���Դ 
            httpclient.getConnectionManager().shutdown(); 
        } 
    } 
   
}