import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;


public class Spider {
	private static String urllog="http://www.pss-system.gov.cn/sipopublicsearch/portal/uilogin-forwardLogin.shtml";
	private static String urlyan="http://www.pss-system.gov.cn/sipopublicsearch/portal//sipopublicsearch/portal/login-showPic.shtml";
	private static BasicCookieStore cookies = new BasicCookieStore();
	static String lll="http://www.pss-system.gov.cn:80/sipopublicsearch/portal/app/uilogin/js/login.js";
	static String login="http://www.pss-system.gov.cn:80/sipopublicsearch/wee/platform/wee_security_check";
	static String ll="http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/";
	public static void main(String args[]){
		
		
		/*
    	String url = "http://www.pss-system.gov.cn/sipopublicsearch/portal/sipopublicsearch/portal/login-showPic.shtml";
        String regex = "\"noResultCode\":0(.+?)\"";
        String result = getpage(url);
        String message = "";
        if(!(message = regexString(result,regex)).equals(""))
        {
        	System.out.println(message);
        }else{
        	System.out.println("获取信息失败");
        }
        */
		
		
		
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet httpGet = new HttpGet(lll);
			
			HttpResponse httpResponse;
			String resultStr = "";
			httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();//得到响应内容
			Header headers[] = httpResponse.getAllHeaders();
			writeCookies(headers);
			for (Header header : headers) {
				String name = header.getName();
				String value = header.getValue();
				System.out.println(name+" "+value);
				}
			//String result = inputStreamToString(entity.getContent());
			
			InputStream inStream = entity.getContent();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] in2b = swapStream.toByteArray();
	        byte[] conbyte = uncompress(in2b);
	        System.out.println(new String(conbyte));
	        InputStream ggg=new ByteArrayInputStream(conbyte);
	        System.out.println("");
	        /*
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null;
			while((line = reader.readLine()) != null) {
			resultStr += line + "\n";
			}
			System.out.println(resultStr);
			
			*/
			/*
	        InputStream in = ggg;
	        File uploadFile = new File("D:/", "yan.jpg");
	        OutputStream out = new FileOutputStream(uploadFile);  
	        byte[] buffer = new byte[1024 * 1024];   
	        int length;   
	        while ((length = in.read(buffer)) > 0) {  
	            out.write(buffer, 0, length);   
	        }
	        in.close();   
	        out.close();
			*/
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String cookieStr = "";
		List<Cookie> list = cookies.getCookies();
		for (Cookie cookie : list) {
		cookieStr += cookie.getName() + "=" + cookie.getValue() + ";";
		}
		
		HttpGet httpGet = new HttpGet(urlyan);

		httpGet.setHeader("Host", "www.pss-system.gov.cn");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Referer", "http://www.pss-system.gov.cn/sipopublicsearch/portal/uilogin-forwardLogin.shtml");
		httpGet.setHeader("Cookie", cookieStr);
		httpGet.setHeader("Connection", "keep-alive");
		HttpResponse httpResponse2=null;
		try {
			httpResponse2 = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Header headers2[] = httpResponse2.getAllHeaders();
		writeCookies(headers2);
		for (Header header : headers2) {
			String name = header.getName();
			String value = header.getValue();
			System.out.println(name+" "+value);
			}
		HttpEntity entity = httpResponse2.getEntity();
		InputStream inStream=null;
		try {
			inStream = entity.getContent();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        try {
			while ((rc = inStream.read(buff, 0, 100)) > 0) {  
			    swapStream.write(buff, 0, rc);  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        byte[] in2b = swapStream.toByteArray();
        byte[] conbyte = uncompress(in2b);
        //System.out.println(new String(conbyte));
        InputStream ggg=new ByteArrayInputStream(conbyte);
		InputStream in = ggg;
        File uploadFile = new File("D:/", "yan.jpg");
        OutputStream out=null;
		try {
			out = new FileOutputStream(uploadFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        byte[] buffer = new byte[1024 * 1024];   
        int length;   
        try {
			while ((length = in.read(buffer)) > 0) {  
			    out.write(buffer, 0, length);   
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        
        
        
        
        
        
        
        
        
        
        
        
        Scanner sc = new Scanner(System.in); 
        String yan = sc.next();
        
        HttpPost httpPost = new HttpPost(login);

        httpPost.setHeader("Host", "www.pss-system.gov.cn");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
        httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Referer", "http://www.pss-system.gov.cn/sipopublicsearch/portal/uilogin-forwardLogin.shtml");
        httpPost.setHeader("Cookie", cookieStr);
        httpPost.setHeader("Connection", "keep-alive");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("j_username", "cheng1275168"));  
        nvps.add(new BasicNameValuePair("j_password", "19960710"));  
        nvps.add(new BasicNameValuePair("j_validation_code", yan)); 
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpResponse httpResponse3=null;
		try {
			httpResponse3 = httpClient.execute(httpPost);
			
			HttpEntity entity2 = httpResponse3.getEntity();//得到响应内容
			Header headers[] = httpResponse3.getAllHeaders();
			writeCookies(headers);
			for (Header header : headers) {
				String name = header.getName();
				String value = header.getValue();
				System.out.println(name+" "+value);
				}
			//String result = inputStreamToString(entity.getContent());
			
			InputStream inStream2 = entity2.getContent();
			swapStream = new ByteArrayOutputStream();  
	        buff = new byte[100];  
	        rc = 0;  
	        while ((rc = inStream2.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        in2b = swapStream.toByteArray();
	        conbyte = uncompress(in2b);
	        System.out.println(new String(conbyte));
	        
			
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
	
	public static byte[] uncompress(byte[] bytes) {  
        if (bytes == null || bytes.length == 0) {  
            return null;
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);  
        try {  
            GZIPInputStream ungzip = new GZIPInputStream(in);  
            byte[] buffer = new byte[256];  
            int n;  
            while ((n = ungzip.read(buffer)) >= 0) {  
                out.write(buffer, 0, n);  
            }
        } catch (IOException e) {  
            //ApiLogger.error("gzip uncompress error.", e);  
        }  
  
        return out.toByteArray();  
    }
	private static String inputStreamToString(InputStream is) {

        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            // Read response until the end
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            //Log.e(TAG, e.getLocalizedMessage(), e);
        }

        // Return full string
        return total.toString();
    }
	
	
	
	
	public static String getpage(String url){
        String result = "";
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                if (response != null
                        && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    //System.out.println(response.getStatusLine());
                    HttpEntity entity = response.getEntity();
                    result = readResponse(entity, "utf-8");
                }
            } finally {
                httpclient.close();
                response.close();
            }
        }catch (java.lang.NoClassDefFoundError e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
	
	
	
	private static String readResponse(HttpEntity resEntity, String charset) {
        StringBuffer res = new StringBuffer();
        BufferedReader reader = null;
        try {
            if (resEntity == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(
                    resEntity.getContent(), charset));
            String line = null;

            while ((line = reader.readLine()) != null) {
                res.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }

        }
        return res.toString();
    }
	
	
	
	
	
	public static String regexString(String targetStr, String patternStr){
        Pattern pattern = Pattern.compile(patternStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        // 如果找到了
        if (matcher.find()) {
            // 打印出结果
            // System.out.println(matcher.group(1));
            return matcher.group(1);
        }
        return "";
    }
	private static void writeCookies(Header[] headers){
		for (Header header : headers) {
			String name = header.getName();
			String value = header.getValue();
			if("Set-Cookie".equalsIgnoreCase(name)) {
				String[] strs = value.split(";");
				for (String str : strs) {
					String[] cookiess = str.split("=");
					if(cookiess.length == 2) {
					cookies.addCookie(new BasicClientCookie(cookiess[0], cookiess[1]));
					} 
					else {
						cookies.addCookie(new BasicClientCookie(cookiess[0], ""));
					}
				}
			}
		}
	}
}
