package com.sch.httptool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpTools {
	private static HttpClient httpclient;
	public HttpTools(){
		httpclient = new DefaultHttpClient();
	}
	private HttpPost GetXHRPost(String url){
		HttpPost httpPost = new HttpPost(url);
	    httpPost.setHeader("Accept", "*/*");
	    httpPost.setHeader("Accept-Encoding", "gzip, deflate");
	    httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
	    httpPost.setHeader("Connection", "keep-alive");
	    httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	    httpPost.setHeader("Cookie", "WEE_SID=qit_a5G4OXp_ivPkmIw2yJH9xO9jOk59l8t1HiID7Jl6T3sU_U_y!-2054955282!220641695!1492491407800; IS_LOGIN=false; avoid_declare=declare_pass; locale=ja_JP; JSESSIONID=qit_a5G4OXp_ivPkmIw2yJH9xO9jOk59l8t1HiID7Jl6T3sU_U_y!-2054955282!220641695; Anonymity_SearchHistory_SessionId=qit_a5G4OXp_ivPkmIw2yJH9xO9jOk59l8t1HiID7Jl6T3sU_U_y!-2054955282!220641695!1492491407800; Anonymity_SearchHistory=; _gscu_761734625=915405825a8pe994; _gscs_761734625=t92491391eyoh3z62|pv:5; _gscbrs_761734625=1");
	    httpPost.setHeader("Host", "www.pss-system.gov.cn");
	    httpPost.setHeader("Origin", "http://www.pss-system.gov.cn");
	    httpPost.setHeader("Referer", "http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/searchHomeIndex-searchHomeIndex.shtml");
	    httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
	    httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
	    return httpPost;
	}
	/*//ByTitle未成功-----------错误版
	private void ByTitleRePost(){
		String url = "http://www.pss-system.gov.cn/sipopublicsearch/portal/paramRewriter-paramEncode.shtml";
		HttpPost httpPost = GetXHRPost(url);
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("params","type=TIVIEW"));
	    try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			HttpResponse response = this.httpclient.execute(httpPost);
			InputStream ff = response.getEntity().getContent();
			httpPost.releaseConnection();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HttpPost GetListHttpPost(String find){
		
		//try {
		//	find = URLEncoder.encode(find, "utf-8");
		//	find = URLDecoder.decode(find, "gbk");
		//} catch (UnsupportedEncodingException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		ByTitleRePost();
		String url = "http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/guichang-executeSmartSearch.shtml";
		HttpPost httpPost = GetXHRPost(url);
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("searchCondition.searchExp", "发明名称=(智能)"));
	    //nvps.add(new BasicNameValuePair("searchCondition.searchExp", "%E6%99%BA%E8%83%BD&search_scope=+AND+((DOC_TYPE%3D((%40%40%40%40I%40%40%40%40)))+OR+(DOC_TYPE%3D((%40%40%40%40U%40%40%40%40)))+OR+(DOC_TYPE%3D((%40%40%40%40D%40%40%40%40))))+AND+((CC%3D(%40%40%40%40HK%40%40%40%40))+OR+(CC%3D(%40%40%40%40MO%40%40%40%40))+OR+(CC%3D(%40%40%40%40TW%40%40%40%40))+OR+(CC%3D(%40%40%40%40EP%40%40%40%40))+OR+(CC%3D(%40%40%40%40WO%40%40%40%40))+OR+(CC%3D(%40%40%40%40US%40%40%40%40))+OR+(CC%3D(%40%40%40%40JP%40%40%40%40))+OR+(CC%3D(%40%40%40%40KR%40%40%40%40))+OR+(CC%3D(%40%40%40%40GB%40%40%40%40))+OR+(CC%3D(%40%40%40%40FR%40%40%40%40))+OR+(CC%3D(%40%40%40%40DE%40%40%40%40))+OR+(CC%3D(%40%40%40%40DD%40%40%40%40))+OR+(CC%3D(%40%40%40%40RU%40%40%40%40))+OR+(CC%3D(%40%40%40%40CH%40%40%40%40))+OR+(CC%3D(%40%40%40%40AT%40%40%40%40))+OR+(CC%3D(%40%40%40%40AU%40%40%40%40))+OR+(CC%3D(%40%40%40%40BE%40%40%40%40))+OR+(CC%3D(%40%40%40%40NL%40%40%40%40))+OR+(CC%3D(%40%40%40%40CA%40%40%40%40))+OR+(CC%3D(%40%40%40%40ES%40%40%40%40))+OR+(CC%3D(%40%40%40%40MX%40%40%40%40))+OR+(CC%3D(%40%40%40%40CN%40%40%40%40)))&searchCondition.dbId=VDB&resultPagination.limit=12&searchCondition.searchType=Sino_foreign&wee.bizlog.modulelevel=0200101"));  
	    nvps.add(new BasicNameValuePair("search_scope"," AND ((DOC_TYPE=((@@@@I@@@@))) OR (DOC_TYPE=((@@@@U@@@@))) OR (DOC_TYPE=((@@@@D@@@@)))) AND ((CC=(@@@@HK@@@@)) OR (CC=(@@@@MO@@@@)) OR (CC=(@@@@TW@@@@)) OR (CC=(@@@@EP@@@@)) OR (CC=(@@@@WO@@@@)) OR (CC=(@@@@US@@@@)) OR (CC=(@@@@JP@@@@)) OR (CC=(@@@@KR@@@@)) OR (CC=(@@@@GB@@@@)) OR (CC=(@@@@FR@@@@)) OR (CC=(@@@@DE@@@@)) OR (CC=(@@@@DD@@@@)) OR (CC=(@@@@RU@@@@)) OR (CC=(@@@@CH@@@@)) OR (CC=(@@@@AT@@@@)) OR (CC=(@@@@AU@@@@)) OR (CC=(@@@@BE@@@@)) OR (CC=(@@@@NL@@@@)) OR (CC=(@@@@CA@@@@)) OR (CC=(@@@@ES@@@@)) OR (CC=(@@@@MX@@@@)) OR (CC=(@@@@CN@@@@)))"));
	    nvps.add(new BasicNameValuePair("searchCondition.dbId","VDB"));
	    nvps.add(new BasicNameValuePair("resultPagination.limit","12"));
	    nvps.add(new BasicNameValuePair("searchCondition.searchType","Sino_foreign"));
	    nvps.add(new BasicNameValuePair("wee.bizlog.modulelevel","0200101"));
	    try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return httpPost;
	}
	*/
	private HttpPost GetListHttpPost(String find,int start){
		if(find==null||find==""||start<0){return null;}
		String starts = String.valueOf(start);
		/*
		try {
			find = URLEncoder.encode(find, "utf-8");
			find = URLDecoder.decode(find, "gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String url = "http://www.pss-system.gov.cn/sipopublicsearch/patentsearch/showSearchResult-startWa.shtml";
		HttpPost httpPost = GetXHRPost(url);
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("searchCondition.searchExp", "发明名称=("+find+")"));
	    //nvps.add(new BasicNameValuePair("searchCondition.searchExp", "%E6%99%BA%E8%83%BD&search_scope=+AND+((DOC_TYPE%3D((%40%40%40%40I%40%40%40%40)))+OR+(DOC_TYPE%3D((%40%40%40%40U%40%40%40%40)))+OR+(DOC_TYPE%3D((%40%40%40%40D%40%40%40%40))))+AND+((CC%3D(%40%40%40%40HK%40%40%40%40))+OR+(CC%3D(%40%40%40%40MO%40%40%40%40))+OR+(CC%3D(%40%40%40%40TW%40%40%40%40))+OR+(CC%3D(%40%40%40%40EP%40%40%40%40))+OR+(CC%3D(%40%40%40%40WO%40%40%40%40))+OR+(CC%3D(%40%40%40%40US%40%40%40%40))+OR+(CC%3D(%40%40%40%40JP%40%40%40%40))+OR+(CC%3D(%40%40%40%40KR%40%40%40%40))+OR+(CC%3D(%40%40%40%40GB%40%40%40%40))+OR+(CC%3D(%40%40%40%40FR%40%40%40%40))+OR+(CC%3D(%40%40%40%40DE%40%40%40%40))+OR+(CC%3D(%40%40%40%40DD%40%40%40%40))+OR+(CC%3D(%40%40%40%40RU%40%40%40%40))+OR+(CC%3D(%40%40%40%40CH%40%40%40%40))+OR+(CC%3D(%40%40%40%40AT%40%40%40%40))+OR+(CC%3D(%40%40%40%40AU%40%40%40%40))+OR+(CC%3D(%40%40%40%40BE%40%40%40%40))+OR+(CC%3D(%40%40%40%40NL%40%40%40%40))+OR+(CC%3D(%40%40%40%40CA%40%40%40%40))+OR+(CC%3D(%40%40%40%40ES%40%40%40%40))+OR+(CC%3D(%40%40%40%40MX%40%40%40%40))+OR+(CC%3D(%40%40%40%40CN%40%40%40%40)))&searchCondition.dbId=VDB&resultPagination.limit=12&searchCondition.searchType=Sino_foreign&wee.bizlog.modulelevel=0200101"));  
	    nvps.add(new BasicNameValuePair("search_scope"," AND ((DOC_TYPE=((\"I\"))) OR (DOC_TYPE=((\"U\"))) OR (DOC_TYPE=((\"D\")))) AND ((CC=(\"HK\")) OR (CC=(\"MO\")) OR (CC=(\"TW\")) OR (CC=(\"EP\")) OR (CC=(\"WO\")) OR (CC=(\"US\")) OR (CC=(\"JP\")) OR (CC=(\"KR\")) OR (CC=(\"GB\")) OR (CC=(\"FR\")) OR (CC=(\"DE\")) OR (CC=(\"DD\")) OR (CC=(\"RU\")) OR (CC=(\"CH\")) OR (CC=(\"AT\")) OR (CC=(\"AU\")) OR (CC=(\"BE\")) OR (CC=(\"NL\")) OR (CC=(\"CA\")) OR (CC=(\"ES\")) OR (CC=(\"MX\")) OR (CC=(\"CN\")))"));
	    nvps.add(new BasicNameValuePair("searchCondition.dbId",""));
	    nvps.add(new BasicNameValuePair("resultPagination.limit","12"));
	    nvps.add(new BasicNameValuePair("searchCondition.searchType","Sino_foreign"));
	    nvps.add(new BasicNameValuePair("wee.bizlog.modulelevel","0200101"));
	    nvps.add(new BasicNameValuePair("searchCondition.executableSearchExp","VDB:((TIVIEW='"+find+"' AND (DOC_TYPE='I' OR DOC_TYPE='U' OR DOC_TYPE='D') AND (CC='HK' OR CC='MO' OR CC='TW' OR CC='EP' OR CC='WO' OR CC='US' OR CC='JP' OR CC='KR' OR CC='GB' OR CC='FR' OR CC='DE' OR CC='DD' OR CC='RU' OR CC='CH' OR CC='AT' OR CC='AU' OR CC='BE' OR CC='NL' OR CC='CA' OR CC='ES' OR CC='MX' OR CC='CN')))"));
	    char[] ff = find.toCharArray();
	    String fff = "";
	    for(char f:ff)fff+="["+f+"][ ]{0,}";
	    nvps.add(new BasicNameValuePair("searchCondition.searchKeywords",","+fff+",[ ]{0,}[W][ ]{0,}[O][ ]{0,}[ ]{0,},[ ]{0,}[U][ ]{0,}[S][ ]{0,}[ ]{0,},[ ]{0,}[T][ ]{0,}[W][ ]{0,}[ ]{0,},[ ]{0,}[R][ ]{0,}[U][ ]{0,}[ ]{0,},[ ]{0,}[N][ ]{0,}[L][ ]{0,}[ ]{0,},[ ]{0,}[M][ ]{0,}[X][ ]{0,}[ ]{0,},[ ]{0,}[M][ ]{0,}[O][ ]{0,}[ ]{0,},[ ]{0,}[K][ ]{0,}[R][ ]{0,}[ ]{0,},[ ]{0,}[J][ ]{0,}[P][ ]{0,}[ ]{0,},[ ]{0,}[H][ ]{0,}[K][ ]{0,}[ ]{0,},[ ]{0,}[G][ ]{0,}[B][ ]{0,}[ ]{0,},[ ]{0,}[F][ ]{0,}[R][ ]{0,}[ ]{0,},[ ]{0,}[E][ ]{0,}[S][ ]{0,}[ ]{0,},[ ]{0,}[E][ ]{0,}[P][ ]{0,}[ ]{0,},[ ]{0,}[D][ ]{0,}[E][ ]{0,}[ ]{0,},[ ]{0,}[D][ ]{0,}[D][ ]{0,}[ ]{0,},[ ]{0,}[C][ ]{0,}[N][ ]{0,}[ ]{0,},[ ]{0,}[C][ ]{0,}[H][ ]{0,}[ ]{0,},[ ]{0,}[C][ ]{0,}[A][ ]{0,}[ ]{0,},[ ]{0,}[B][ ]{0,}[E][ ]{0,}[ ]{0,},[ ]{0,}[A][ ]{0,}[U][ ]{0,}[ ]{0,},[ ]{0,}[A][ ]{0,}[T][ ]{0,}[ ]{0,}"));
	    nvps.add(new BasicNameValuePair("searchCondition.literatureSF",""));
	    nvps.add(new BasicNameValuePair("searchCondition.strategy",""));
	    nvps.add(new BasicNameValuePair("resultPagination.totalCount",""));//应该有得到的总条数
	    nvps.add(new BasicNameValuePair("resultPagination.start",starts));
	    nvps.add(new BasicNameValuePair("resultPagination.sumLimit","10"));
	    try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return httpPost;
	}
	private HttpPost GetScanHttpPost(String nrdAn,String sid){
		String url = "http://www.pss-system.gov.cn:80/sipopublicsearch/patentsearch/queryAbstractInfo-viewAbstractInfo.shtml";
		HttpPost httpPost = GetXHRPost(url);
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
	    nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("nrdAn",nrdAn));
	    nvps.add(new BasicNameValuePair("cid",sid));
	    nvps.add(new BasicNameValuePair("sid",sid));
	    nvps.add(new BasicNameValuePair("wee.bizlog.modulelevel","0201101"));
	    try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return httpPost;
	}
	private String GetHttpContext(HttpPost httppost){
		try {
			HttpResponse response = this.httpclient.execute(httppost);
			/*
			Header headers[] = response.getAllHeaders();
			for (Header header : headers) {
				String name = header.getName();
				String value = header.getValue();
				System.out.println(name+" "+value);
				}
			*/
			GZIPInputStream gzip =new GZIPInputStream(response.getEntity().getContent());
			StringBuffer res = new StringBuffer();
			BufferedReader reader = null;
			reader = new BufferedReader(new InputStreamReader(gzip,"utf-8"));
			String line = null;
            while ((line = reader.readLine()) != null) {
                res.append(line);
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
			httppost.releaseConnection();
			return res.toString();
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;
	}
	
	
	public List<ListTitle> GetListMess(String res,int start,Boolean bool){
		HttpPost httppost = GetListHttpPost(res,start);
		if(httppost==null)return null;
		String retu = GetHttpContext(httppost);
		if(retu==null||retu=="")return null;
		//解析retu字符串
		String str = "<div class=\"item\">(.+?)<div class=\"item-header clear\">";
		String getnrdAn = "<input type=\"hidden\" name=\"nrdAnHidden\" value='(.+?)'>";
		String getsid = "<input type=\"hidden\" name=\"idHidden\" value='(.+?)'>";
		String gettit = "<input type=\"hidden\" name=\"titleHidden\" value='(.+?)'>";
		String num = "<input type=\"hidden\" id=\"result_totalCount\" value=\"(.+?)\">";
		List<ListTitle> lists = new ArrayList<ListTitle>();
		Pattern pattern = Pattern.compile(str);
		Pattern patternnrdAn = Pattern.compile(getnrdAn);
		Pattern patternid = Pattern.compile(getsid);
		Pattern patternti = Pattern.compile(gettit);
		Pattern pnum = Pattern.compile(num);
		Matcher mnum = pnum.matcher(retu);
		String nnn="";
		if(mnum.find()){
			nnn = mnum.group(1);
			if(bool)System.out.println("搜索到专利"+nnn+"条");
			if(nnn=="0")return null;
			}
        Matcher matcher = pattern.matcher(retu);
        while(matcher.find()){
        	ListTitle ti = new ListTitle();
        	if(nnn!="")ti.setAllcount(nnn);
        	Matcher matchernrdAn = patternnrdAn.matcher(matcher.group(1));
        	if(matchernrdAn.find()){
        		ti.setNrdAn(matchernrdAn.group(1));
        		//System.out.printf(ti.getNrdAn()+"-----");
        		}
        	Matcher matcherid = patternid.matcher(matcher.group(1));
        	if(matcherid.find()){
        		ti.setSid(matcherid.group(1));
        		//System.out.printf(ti.getSid()+"-----");
        		}
        	Matcher matcherti = patternti.matcher(matcher.group(1));
        	if(matcherti.find()){
        		String title = matcherti.group(1);
        		title = title.replaceAll("<FONT>", "");
        		title = title.replaceAll("</FONT>", "");
        		ti.setTitle(title);
        		if(bool)System.out.println(title);
        		}
        	lists.add(ti);
        }
		return lists;
	}
	public List<ListTitle> GetListMess(String res){
		return GetListMess(res,0,false);
	}
	
	public ScanContext GetScanMess(ListTitle tit,Boolean bool){
		HttpPost httppost = GetScanHttpPost(tit.getNrdAn(),tit.getSid());
		String retu = GetHttpContext(httppost);
		if(retu==null||retu=="")return null;
		ScanContext sc = new ScanContext();
		JSONObject retujson = JSONObject.fromObject(retu);
		JSONObject json = retujson.getJSONObject("abstractInfoDTO");
		JSONArray abIndexList = json.getJSONArray("abIndexList");
		if(abIndexList.size()>0){
			JSONObject abIndex = abIndexList.getJSONObject(0);
			String context = abIndex.getString("value");
			context = context.replaceAll("<.+?>","").trim();
			sc.setContext(context);
			if(bool)System.out.println("详细信息----"+context);
		}
		JSONArray abstractItemList = json.getJSONArray("abstractItemList");
		for (int i = 0; i < abstractItemList.size(); i++) {
			JSONObject result = abstractItemList.getJSONObject(i);
			String name = result.getString("indexCnName");
			String sign = result.getString("indexCode");
			name = name.replaceAll("&shy;", "");
			String val = result.getString("value").trim();
			if(bool)System.out.println(name+"----"+val);
			if(sign!=null){
				if(sign.equals("APO"))sc.setRequestnum(val);
				else if(sign.equals("APD"))sc.setRequestday(val);
				else if(sign.equals("PN"))sc.setNotificationnum(val);
				else if(sign.equals("PD"))sc.setNotificationday(val);
				else if(sign.equals("ICST"))sc.setIpc(val);
				else if(sign.equals("PAVIEW"))sc.setRequestname(val);
				else if(sign.equals("INVIEW"))sc.setCreatename(val);
				else if(sign.equals("PR"))sc.setPrioritynum(val);
				else if(sign.equals("PRD"))sc.setPriorityday(val);
				else if(sign.equals("AA"))sc.setAddress(val);
				else if(sign.equals("AZ"))sc.setPostcode(val);
			}
		}
		JSONObject title = json.getJSONObject("tioIndex");
		if(bool)System.out.println(title.getString("indexCnName")+"----"+title.getString("value").trim());
		sc.setTitle(title.getString("value").trim());
		return sc;
	}
	public ScanContext GetScanMess(ListTitle tit){
		return GetScanMess(tit,false);
	}
}
