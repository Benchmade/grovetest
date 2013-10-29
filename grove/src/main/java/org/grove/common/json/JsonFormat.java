package org.grove.common.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonFormat {

	@Test
	public void format() throws Exception {
		String ext = "m	none	1433153797	2745089509	1101016735	-	797858329";
		ArrayList<String> extArray = format(ext);
		for(String str : extArray){
			System.out.println(str);
		}
	}

	private ArrayList<String> format(String extend) {
		
		String[] extField = extend.split("\t");

		JsonElement dataNode = getJsonData("wbv4", "1", "1");
		JsonArray definedArray = dataNode.getAsJsonObject().get("def").getAsJsonArray();
		JsonArray transArray = dataNode.getAsJsonObject().get("trans").getAsJsonArray();

		int defineSize = definedArray.size(), transSize = transArray.size();

		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		// 获得定义，并做好映射，map的key为定义中的key名，value为int类型的位置，这个位置也就是log中extend字段拆分后的位置
		for (int i = 0; i < defineSize; i++) {
			JsonElement definedElement = definedArray.get(i); // {"key":"gender","type":"e","req":"1","opt":"m,f,n","desc":"xxx"}
			String mapKey = definedElement.getAsJsonObject().get("key").getAsString();
			map.put(mapKey, i);
		}

		ArrayList<String> transLog = new ArrayList<String>();
		
		for (int i = 0; i < transSize; i++) {
			Set<Entry<String, JsonElement>> elements = transArray.get(i).getAsJsonObject().entrySet();
			/*elements
			{"gender":{"type":"keep","value":""},
			 "verified":{"type":"keep","value":""},
			 "ad":{"type":"remap","value":"ad1"},
			 "frame":{"type":"extend","value":"1"}},*/
			StringBuilder sb = new StringBuilder();
			
			for (Entry<String, JsonElement> entry : elements) {
				JsonObject defined = entry.getValue().getAsJsonObject();
				
				String type = defined.get("type").getAsString(); //"type":"extend","value":"1"
				String value = defined.get("value").getAsString();
				if(type.equals("keep")){//如果是keep，说明不用变，用gender直接去map中取pos信息
					int pos = map.get(entry.getKey());
					if(extField[pos].equals("-")){
						sb = null;
						break;
					}else{
						sb.append(extField[pos]).append("\t");
					}
				}else if(type.equals("remap")){
					int pos = map.get(value);
					if(extField[pos].equals("-")){
						sb = null;
						break;
					}else{
						sb.append(extField[pos]).append("\t");
					}
				}else if(type.equals("extend")){
					sb.append(value).append("\t");
				}
			}
			if(sb!=null){
				transLog.add(sb.toString().trim());
			}
		}

		return transLog;
	}

	/**
	 * 得到data节点，获得数据定义和扩展的方式。通过产品编码，行为码，版本获得
	 * 
	 * @param product
	 * @param act
	 * @param ver
	 * @return 返回data节点的 JsonElement
	 */
	private JsonElement getJsonData(String product, String act, String ver) {
		JsonElement data = null;

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpget = new HttpPost("http://data.admin.weibo.com/datasource/processrule?product="+ product + "&act=" + act + "&ver=" + ver);
		System.out.println(httpget.getURI());
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody;
		try {
			responseBody = httpclient.execute(httpget, responseHandler);
			JsonElement root = new JsonParser().parse(responseBody);
			int errorCode = root.getAsJsonObject().get("error").getAsInt();// 获得错误码，如果error是0，说明有定义，如果是1，说明出错
			if (errorCode == 0) {
				data = root.getAsJsonObject().get("data");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpclient.getConnectionManager().shutdown();
		return data;
	}

}
