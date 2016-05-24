package com.ywg.webcatch.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class PhoneCatchService {
	public String getPhone(String num) {
		// Map<String, String> cookies = new HashMap<String, String>();
		// cookies.put("ssuid", "7598801950");
		// cookies.put("IPLOC", "CN3201");
		// cookies.put("SUID", "42B3E2DD4418920A000000005372CC87");
		// cookies.put("usid", "U7ViMi0NubGp6-HH");
		// cookies.put("SUV", "001F50B6DDE2B34253D5A3163083C283");
		// cookies.put("sct", "2");
		// cookies.put("SMYUV", "1416287666745559");
		// cookies.put("PHPSESSID", "flkup3or7s6is4dpbdfgbp15k5");
		// Map<String, String> data = new HashMap<String, String>();
		// data.put("Host", "data.haoma.sogou.com");
		// String url =
		// "http://data.haoma.sogou.com/vrapi/query_number.php?number="
		// + num + "&type=json&callback=show";
		// try {
		// Document document = Jsoup
		// .connect(url)
		// .referrer(
		// "http://www.sogou.com/web?query=13622334643&ie=utf8&_ast=1422327552&_asf=null&w=01029901&p=40040110&dp=1&cid=")
		// .userAgent(
		// "Mozilla/5.0 (Windows NT 6.1; rv:35.0) Gecko/20100101 Firefox/35.0")
		// .cookies(cookies).data(data).ignoreContentType(true).get();
		// String html = document.toString();
		// String body = html.substring(html.indexOf("show(") + 5,
		// html.indexOf(")")).replace("&quot;", "\"");
		// JSONObject json = JSONObject.fromObject(body);
		// String numinfo = json.getString("NumInfo").replace("：0", "");
		// String amount = json.getString("Amount");
		// System.err.println(numinfo + "----" + amount);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpGet httpGet = new HttpGet(
				"http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/s?word="
						+ num
						+ "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=usm%400%2Cta%40middle____%2Csz%40224_220&idx=20000&tn_1=middle&tn_2=middle&ct_1=%E6%90%9C%E7%BD%91%E9%A1%B5");
		try {
			// 执行get请求
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			// System.out.println("status:" + httpResponse.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				InputStream is = entity.getContent();
				// ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte buffer[] = new byte[500];
				int len = 0;
				StringBuffer html = new StringBuffer();
				while ((len = is.read(buffer)) != -1) {
					// 根据读取的长度写入到字节数组输出流对象中
					// os.write(buffer, 0, len);
					html.append(new String(buffer, "UTF-8"));
					// if(html.toString().contains("<p class=\"wa_liarphone2_text\">")){
					// System.err.println(new String(buffer, "UTF-8"));
					// break;
					// }
					if (html.toString()
							.matches(
									"[\\w\\W]*(<p class=\"wa_liarphone2_text\">)[\\w\\W]*(</p>)[\\w\\W]*")) {
						is.close();
						break;
					}
				}
				System.err.println(html.toString().length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { // 关闭流并释放资源
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
