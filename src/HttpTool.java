import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;

public class HttpTool {
	private static String urllogin = "http://www.pss-system.gov.cn/sipopublicsearch/portal/uilogin-forwardLogin.shtml";
	private static HttpClient httpClient;
	private static BasicCookieStore cookies = new BasicCookieStore();
	HttpTool(){
		try{
			httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(urllogin);
			HttpResponse response = null;
			byte[] getbyte=new byte[]{};
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeCookies(response.getAllHeaders());
			try {
				InputStream inStream = response.getEntity().getContent();
				getbyte = uncompress(inStream);
			} catch (UnsupportedOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	public HttpResponse GetHttpResponse(HttpGet httpGet){
		HttpResponse response=null;
		try {
			response = httpClient.execute(httpGet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	private void writeCookies(Header[] headers){
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
	private byte[] uncompress(InputStream inStream) {  
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        try {
			while ((rc = inStream.read(buff, 0, 100)) > 0) {  
			    swapStream.write(buff, 0, rc);  
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        byte[] bytes = swapStream.toByteArray();
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
	
}
