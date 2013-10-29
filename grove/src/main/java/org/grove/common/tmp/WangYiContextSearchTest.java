package org.grove.common.tmp;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.mortbay.util.UrlEncoded;

public class WangYiContextSearchTest {

	public static void main(String[] args) throws Exception {

		String proxyAddr = "http://tools.search.taobao.com:9999/proxy.php?url=";

		String contextEngine = "http%3A%2F%2F10.246.7.208%3A2087%2Fbin%2Fsp%3Fsrc%3Dss-srp-s006014.cm8%26outfmt%3Djson2%26app%3Dts_detail%26navigator%3Dall%26n%3D10%26s%3D0%26xq_bucket%3D2%26et%3D1374561895%26tab%3Dtmall%26q%3D";

		Pattern pdf = Pattern.compile("\"DocsFound\" : (\\d*)");
		Pattern pnid = Pattern.compile("\"nid\" : \"(\\d*)\"");

		PoolingClientConnectionManager conMan = new PoolingClientConnectionManager(SchemeRegistryFactory.createDefault());
		conMan.setMaxTotal(20);
		conMan.setDefaultMaxPerRoute(20);

		List<String> allLine = Files.readAllLines(Paths.get("d:/noresult.txt"), Charset.forName("utf-8"));

		FileWriter fw = new FileWriter(new File("d:/xxx"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		for (String line : allLine) {
			String surl = proxyAddr + contextEngine + UrlEncoded.encodeString(UrlEncoded.encodeString(line, "utf-8"), "utf-8");
			HttpGet httpget = new HttpGet(surl);
			HttpResponse response;
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			byte[] bytes = IOUtils.toByteArray(is);
			String xxx = new String(bytes);
			Matcher match = pdf.matcher(xxx);
			StringBuilder sb = new StringBuilder();
			sb.append("<tr><td>"+line+"</td>"); 
			if(match.find()){
				sb.append("<td>"+match.group(1)+"</td>");
			}
			Matcher matchnid = pnid.matcher(xxx);
			int i=0;
			sb.append("<td>");
			while(matchnid.find()){
				sb.append("<a href=\"http://detail.tmall.com/item.htm?id="+matchnid.group(1)+"\" target=\"_blank\">(" + String.valueOf(i++) +")</a>&nbsp");
			}
			sb.append("</td><td><a href=\"").append(surl).append("\" target=\"_blank\">url</a></td></tr>");
			is.close();
			httpget.abort();
			fw.write(sb.toString());
			fw.write("\n");
		}
		fw.close();
		httpclient.getConnectionManager().shutdown();
		
	}
}
