import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class gggg {
	static String spider="http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/guichang-executeSmartSearch.shtml";
	static String scan="http://www.pss-system.gov.cn:80/sipopublicsearch/patentsearch/showViewList-jumpToView.shtml";
	static String scanmess="http://www.pss-system.gov.cn:80/sipopublicsearch/patentsearch/queryAbstractInfo-viewAbstractInfo.shtml";
	public static void main(String args[]){
		
		
		HttpClient httpClient = new DefaultHttpClient();
		
		
		
		//关键词搜索页面
		HttpPost httpPost = new HttpPost(spider);

	    httpPost.setHeader("Host", "www.pss-system.gov.cn");
	    httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
	    httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
	    httpPost.setHeader("Accept-Encoding", "gzip, deflate");
	    httpPost.setHeader("Referer", "http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/searchHomeIndex-searchHomeIndex.shtml");
	    httpPost.setHeader("Connection", "keep-alive");
	    httpPost.setHeader("Origin", "http://www.pss-system.gov.cn");
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("searchCondition.searchExp", "robot"));
	    //nvps.add(new BasicNameValuePair("searchCondition.searchExp", "%E6%99%BA%E8%83%BD&search_scope=+AND+((DOC_TYPE%3D((%40%40%40%40I%40%40%40%40)))+OR+(DOC_TYPE%3D((%40%40%40%40U%40%40%40%40)))+OR+(DOC_TYPE%3D((%40%40%40%40D%40%40%40%40))))+AND+((CC%3D(%40%40%40%40HK%40%40%40%40))+OR+(CC%3D(%40%40%40%40MO%40%40%40%40))+OR+(CC%3D(%40%40%40%40TW%40%40%40%40))+OR+(CC%3D(%40%40%40%40EP%40%40%40%40))+OR+(CC%3D(%40%40%40%40WO%40%40%40%40))+OR+(CC%3D(%40%40%40%40US%40%40%40%40))+OR+(CC%3D(%40%40%40%40JP%40%40%40%40))+OR+(CC%3D(%40%40%40%40KR%40%40%40%40))+OR+(CC%3D(%40%40%40%40GB%40%40%40%40))+OR+(CC%3D(%40%40%40%40FR%40%40%40%40))+OR+(CC%3D(%40%40%40%40DE%40%40%40%40))+OR+(CC%3D(%40%40%40%40DD%40%40%40%40))+OR+(CC%3D(%40%40%40%40RU%40%40%40%40))+OR+(CC%3D(%40%40%40%40CH%40%40%40%40))+OR+(CC%3D(%40%40%40%40AT%40%40%40%40))+OR+(CC%3D(%40%40%40%40AU%40%40%40%40))+OR+(CC%3D(%40%40%40%40BE%40%40%40%40))+OR+(CC%3D(%40%40%40%40NL%40%40%40%40))+OR+(CC%3D(%40%40%40%40CA%40%40%40%40))+OR+(CC%3D(%40%40%40%40ES%40%40%40%40))+OR+(CC%3D(%40%40%40%40MX%40%40%40%40))+OR+(CC%3D(%40%40%40%40CN%40%40%40%40)))&searchCondition.dbId=VDB&resultPagination.limit=12&searchCondition.searchType=Sino_foreign&wee.bizlog.modulelevel=0200101"));  
	    nvps.add(new BasicNameValuePair("search_scope"," AND ((DOC_TYPE=((\"I\"))) OR (DOC_TYPE=((\"U\"))) OR (DOC_TYPE=((\"D\")))) AND ((CC=(\"HK\")) OR (CC=(\"MO\")) OR (CC=(\"TW\")) OR (CC=(\"EP\")) OR (CC=(\"WO\")) OR (CC=(\"US\")) OR (CC=(\"JP\")) OR (CC=(\"KR\")) OR (CC=(\"GB\")) OR (CC=(\"FR\")) OR (CC=(\"DE\")) OR (CC=(\"DD\")) OR (CC=(\"RU\")) OR (CC=(\"CH\")) OR (CC=(\"AT\")) OR (CC=(\"AU\")) OR (CC=(\"BE\")) OR (CC=(\"NL\")) OR (CC=(\"CA\")) OR (CC=(\"ES\")) OR (CC=(\"MX\")) OR (CC=(\"CN\")))"));
	    nvps.add(new BasicNameValuePair("searchCondition.dbId","VDB"));
	    nvps.add(new BasicNameValuePair("resultPagination.limit","12"));
	    nvps.add(new BasicNameValuePair("searchCondition.searchType","Sino_foreign"));
	    nvps.add(new BasicNameValuePair("wee.bizlog.modulelevel","0200101"));
	    try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	    	HttpResponse httpResponse3=null;
		
			httpResponse3 = httpClient.execute(httpPost);
			
			HttpEntity entity2 = httpResponse3.getEntity();//得到响应内容
			Header headers[] = httpResponse3.getAllHeaders();
			for (Header header : headers) {
				String name = header.getName();
				String value = header.getValue();
				System.out.println(name+" "+value);
				}
			//String result = inputStreamToString(entity.getContent());
			//System.out.println(EntityUtils.toString(entity2));
			InputStream inStream2 = entity2.getContent();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = inStream2.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] in2b = swapStream.toByteArray();
	        byte[] conbyte = uncompress(in2b);
	     //   System.out.println(new String(conbyte,"utf-8"));
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		//详细页面爬取
		
		httpPost = new HttpPost(scan);

	    httpPost.setHeader("Host", "www.pss-system.gov.cn");
	    httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
	    httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
	    httpPost.setHeader("Accept-Encoding", "gzip, deflate");
	    httpPost.setHeader("Referer", "http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/searchHomeIndex-searchHomeIndex.shtml");
	    httpPost.setHeader("Connection", "keep-alive");
	    httpPost.setHeader("Origin", "http://www.pss-system.gov.cn");
	    nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("viewQC.viewLiteraQCList[0].srcCnName", "检索式:其他检索"));
	    nvps.add(new BasicNameValuePair("viewQC.viewLiteraQCList[0].srcEnName","SearchStatement:Other Search"));
	    nvps.add(new BasicNameValuePair("viewQC.viewLiteraQCList[0].searchStrategy",""));
	    nvps.add(new BasicNameValuePair("viewQC.viewLiteraQCList[0].searchCondition.executableSearchExp","VDB:(ID='CN201610062948.920160420FM')"));
	    nvps.add(new BasicNameValuePair("viewQC.viewLiteraQCList[0].searchCondition.sortFields","-APD,+PD"));
	    nvps.add(new BasicNameValuePair("viewQC.needSearch","true"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[0]","[智][ ]{0,}[能][ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[1]","[ ]{0,}[W][ ]{0,}[O][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[2]","[ ]{0,}[U][ ]{0,}[S][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[3]","[ ]{0,}[T][ ]{0,}[W][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[4]","[ ]{0,}[R][ ]{0,}[U][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[5]","[ ]{0,}[N][ ]{0,}[L][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[6]","[ ]{0,}[M][ ]{0,}[X][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[7]","[ ]{0,}[M][ ]{0,}[O][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[8]","[ ]{0,}[K][ ]{0,}[R][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[9]","[ ]{0,}[J][ ]{0,}[P][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[10]","[ ]{0,}[H][ ]{0,}[K][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[11]","[ ]{0,}[G][ ]{0,}[B][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[12]","[ ]{0,}[F][ ]{0,}[R][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[13]","[ ]{0,}[E][ ]{0,}[S][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[14]","[ ]{0,}[E][ ]{0,}[P][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[15]","[ ]{0,}[D][ ]{0,}[E][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[16]","[ ]{0,}[D][ ]{0,}[D][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[17]","[ ]{0,}[C][ ]{0,}[N][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[18]","[ ]{0,}[C][ ]{0,}[H][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[19]","[ ]{0,}[C][ ]{0,}[A][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[20]","[ ]{0,}[B][ ]{0,}[E][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[21]","[ ]{0,}[A][ ]{0,}[U][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.searchKeywords[22]","[ ]{0,}[A][ ]{0,}[T][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("viewQC.type","SEARCH"));
	    nvps.add(new BasicNameValuePair("wee.bizlog.modulelevel","0200604"));
	    try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	    	HttpResponse httpResponse3=null;
			httpResponse3 = httpClient.execute(httpPost);
			
			HttpEntity entity2 = httpResponse3.getEntity();//得到响应内容
			Header headers[] = httpResponse3.getAllHeaders();
			for (Header header : headers) {
				String name = header.getName();
				String value = header.getValue();
				System.out.println(name+" "+value);
				}
			//String result = inputStreamToString(entity.getContent());
			
			InputStream inStream2 = entity2.getContent();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = inStream2.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] in2b = swapStream.toByteArray();
	        byte[] conbyte = uncompress(in2b);
	        System.out.println("..................."+new String(conbyte,"utf-8"));
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		
		
		
		
		
		
		
		
		
		//详细信息爬取
		
		httpPost = new HttpPost(scanmess);

	    httpPost.setHeader("Host", "www.pss-system.gov.cn");
	    httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
	    httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
	    httpPost.setHeader("Accept-Encoding", "gzip, deflate");
	    httpPost.setHeader("Referer", "http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/showViewList-jumpToView.shtml");
	    httpPost.setHeader("Connection", "keep-alive");
	    httpPost.setHeader("Origin", "http://www.pss-system.gov.cn");
	    nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("nrdAn","CN201310390804"));
	    nvps.add(new BasicNameValuePair("cid","CN201310390804.220150318FM"));
	    nvps.add(new BasicNameValuePair("sid","CN201310390804.220150318FM"));
	    nvps.add(new BasicNameValuePair("wee.bizlog.modulelevel","0201101"));
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
			for (Header header : headers) {
				String name = header.getName();
				String value = header.getValue();
				System.out.println(name+" "+value);
				}
			//String result = inputStreamToString(entity.getContent());
			
			InputStream inStream2 = entity2.getContent();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = inStream2.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] in2b = swapStream.toByteArray();
	        byte[] conbyte = uncompress(in2b);
	        System.out.println(new String(conbyte,"utf-8"));
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
}
