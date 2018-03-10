package com.sch.httptool;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class main {
	static String spider="http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/guichang-executeSmartSearch.shtml";
	static String url="http://www.pss-system.gov.cn/sipopublicsearch/portal/app/home/declare.jsp";
	public static void main(String args[]){
		/*
		String find = "resultPagination.limit=12&resultPagination.sumLimit=10&resultPagination.start=12&resultPagination.totalCount=207547&searchCondition.searchType=Sino_foreign&searchCondition.dbId=&searchCondition.strategy=&searchCondition.literatureSF=&search_scope=+AND+%28%28DOC_TYPE%3D%28%28%40%40%40%40I%40%40%40%40%29%29%29+OR+%28DOC_TYPE%3D%28%28%40%40%40%40U%40%40%40%40%29%29%29+OR+%28DOC_TYPE%3D%28%28%40%40%40%40D%40%40%40%40%29%29%29%29+AND+%28%28CC%3D%28%40%40%40%40HK%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40MO%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40TW%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40EP%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40WO%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40US%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40JP%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40KR%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40GB%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40FR%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40DE%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40DD%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40RU%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40CH%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40AT%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40AU%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40BE%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40NL%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40CA%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40ES%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40MX%40%40%40%40%29%29+OR+%28CC%3D%28%40%40%40%40CN%40%40%40%40%29%29%29&searchCondition.searchKeywords=%2C%5B%D6%C7%5D%5B+%5D%7B0%2C%7D%5B%C4%DC%5D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BW%5D%5B+%5D%7B0%2C%7D%5BO%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BU%5D%5B+%5D%7B0%2C%7D%5BS%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BT%5D%5B+%5D%7B0%2C%7D%5BW%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BR%5D%5B+%5D%7B0%2C%7D%5BU%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BN%5D%5B+%5D%7B0%2C%7D%5BL%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BM%5D%5B+%5D%7B0%2C%7D%5BX%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BM%5D%5B+%5D%7B0%2C%7D%5BO%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BK%5D%5B+%5D%7B0%2C%7D%5BR%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BJ%5D%5B+%5D%7B0%2C%7D%5BP%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BH%5D%5B+%5D%7B0%2C%7D%5BK%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BG%5D%5B+%5D%7B0%2C%7D%5BB%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BF%5D%5B+%5D%7B0%2C%7D%5BR%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BE%5D%5B+%5D%7B0%2C%7D%5BS%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BE%5D%5B+%5D%7B0%2C%7D%5BP%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BD%5D%5B+%5D%7B0%2C%7D%5BE%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BD%5D%5B+%5D%7B0%2C%7D%5BD%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BC%5D%5B+%5D%7B0%2C%7D%5BN%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BC%5D%5B+%5D%7B0%2C%7D%5BH%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BC%5D%5B+%5D%7B0%2C%7D%5BA%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BB%5D%5B+%5D%7B0%2C%7D%5BE%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BA%5D%5B+%5D%7B0%2C%7D%5BU%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D%2C%5B+%5D%7B0%2C%7D%5BA%5D%5B+%5D%7B0%2C%7D%5BT%5D%5B+%5D%7B0%2C%7D%5B+%5D%7B0%2C%7D&searchCondition.searchExp=%B7%A2%C3%F7%C3%FB%B3%C6%3D%28%D6%C7%C4%DC%29&resultPagination.sumLimit=10&searchCondition.executableSearchExp=VDB%3A%28%28TIVIEW%3D%27%D6%C7%C4%DC%27+AND+%28DOC_TYPE%3D%27I%27+OR+DOC_TYPE%3D%27U%27+OR+DOC_TYPE%3D%27D%27%29+AND+%28CC%3D%27HK%27+OR+CC%3D%27MO%27+OR+CC%3D%27TW%27+OR+CC%3D%27EP%27+OR+CC%3D%27WO%27+OR+CC%3D%27US%27+OR+CC%3D%27JP%27+OR+CC%3D%27KR%27+OR+CC%3D%27GB%27+OR+CC%3D%27FR%27+OR+CC%3D%27DE%27+OR+CC%3D%27DD%27+OR+CC%3D%27RU%27+OR+CC%3D%27CH%27+OR+CC%3D%27AT%27+OR+CC%3D%27AU%27+OR+CC%3D%27BE%27+OR+CC%3D%27NL%27+OR+CC%3D%27CA%27+OR+CC%3D%27ES%27+OR+CC%3D%27MX%27+OR+CC%3D%27CN%27%29%29%29&wee.bizlog.modulelevel=0200101";		
		try {
			//find = URLEncoder.encode(find, "utf-8");
			find = URLDecoder.decode(find, "gbk");
			System.out.println(find);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		String dd="智能";
		char[] ff = dd.toCharArray();
		for(char f:ff)System.out.println(f);
		*/
		
		//String cn = "CN201310390804.220150318FM";
		HttpTools httptool = new HttpTools();
		/*
		HttpPost htost = httptool.GetListHttpPost("gggggggggggg",0);
		String ret = httptool.GetHttpContext(htost);
		System.out.println(ret);
		*/
		
		
		List<ListTitle> ti = httptool.GetListMess("大数据",0,true);
		for(ListTitle t:ti){
			/*
			System.out.println(t.getAllcount());
			System.out.println(t.getTitle());
			*/
			
			ScanContext r=httptool.GetScanMess(t,true);
			/*
			ScanContext r=httptool.GetScanMess(t);
			System.out.println(r.getTitle());
			System.out.println(r.getRequestnum());
			System.out.println(r.getRequestday());
			System.out.println(r.getNotificationnum());
			System.out.println(r.getNotificationday());
			System.out.println(r.getIpc());
			System.out.println(r.getRequestname());
			System.out.println(r.getCreatename());
			System.out.println(r.getPrioritynum());
			System.out.println(r.getPriorityday());
			System.out.println(r.getAddress());
			System.out.println(r.getPostcode());
			System.out.println(r.getContext());
			*/
		}
		
		
    }
}
