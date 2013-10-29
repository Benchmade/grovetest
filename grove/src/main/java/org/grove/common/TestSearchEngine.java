package org.grove.common;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.client.protocol.RequestProxyAuthentication;
import org.apache.http.client.protocol.RequestTargetAuthentication;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.RequestDate;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestUserAgent;

public class TestSearchEngine {
	/*
	 * static HttpClient httpclient;
	 * 
	 * static class TestHttp implements Callable<Long> {
	 * 
	 * @Override public Long call() throws Exception { long s =
	 * System.currentTimeMillis(); for (int i = 0; i < 10; i++) { HttpGet
	 * httpget = new HttpGet(
	 * "http://10.232.43.8:8000/qp?usernid=-1&rqtest=dynamic&s=mall&c=2&src=tmall-search_10.13.134.19&k=pp&rc=2&nopt=1"
	 * );
	 * 
	 * HttpResponse response; try { response = httpclient.execute(httpget);
	 * HttpEntity entity = response.getEntity(); InputStream is =
	 * entity.getContent(); is.read(); is.close(); } catch
	 * (ClientProtocolException e) { e.printStackTrace(); } catch (IOException
	 * e) { e.printStackTrace(); } httpget.abort(); Thread.sleep(100); } return
	 * System.currentTimeMillis() - s; } }
	 */

	public static void main(String[] args) throws Exception {

		/*
		 * PoolingClientConnectionManager conMan = new
		 * PoolingClientConnectionManager(
		 * SchemeRegistryFactory.createDefault()); conMan.setMaxTotal(200);
		 * conMan.setDefaultMaxPerRoute(200);
		 * 
		 * httpclient = new DefaultHttpClient(conMan); HttpParams params =
		 * httpclient.getParams();
		 * HttpConnectionParams.setConnectionTimeout(params, 20000);
		 * HttpConnectionParams.setSoTimeout(params, 15000);
		 * 
		 * ExecutorService es = Executors.newFixedThreadPool(10); Future<Long>
		 * l1 = es.submit(new TestHttp()); Future<Long> l2 = es.submit(new
		 * TestHttp()); Future<Long> l3 = es.submit(new TestHttp());
		 * Future<Long> l4 = es.submit(new TestHttp()); Future<Long> l5 =
		 * es.submit(new TestHttp());
		 * 
		 * es.shutdown(); while (!es.isTerminated()) { es.awaitTermination(2,
		 * TimeUnit.SECONDS); } httpclient.getConnectionManager().shutdown();
		 * 
		 * System.out.println(l1.get()); System.out.println(l2.get());
		 * System.out.println(l3.get()); System.out.println(l4.get());
		 * System.out.println(l5.get());
		 */
		PoolingClientConnectionManager conMan = new PoolingClientConnectionManager(
				SchemeRegistryFactory.createDefault());
		conMan.setMaxTotal(20);
		conMan.setDefaultMaxPerRoute(20);
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
 		HttpParams params = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 2000);
		HttpConnectionParams.setSoTimeout(params, 1500);
		HttpConnectionParams.setConnectionTimeout(params, 2000);
		HttpConnectionParams.setSoTimeout(params, 1500);
		//HttpConnectionParams.setTcpNoDelay(params, true);
		//HttpConnectionParams.setLinger(params, 1000);
		//HttpConnectionParams.setStaleCheckingEnabled(params, false);
		httpclient.removeRequestInterceptorByClass(RequestExpectContinue.class);
		httpclient.removeRequestInterceptorByClass(RequestAddCookies.class);
		httpclient.removeRequestInterceptorByClass(RequestUserAgent.class);
		httpclient.removeRequestInterceptorByClass(RequestDate.class);
		httpclient.removeRequestInterceptorByClass(RequestDefaultHeaders.class);
		//httpclient.removeRequestInterceptorByClass(RequestContent.class);
		//httpclient.removeRequestInterceptorByClass(RequestConnControl.class);
		//httpclient.removeRequestInterceptorByClass(RequestProxyAuthentication.class);
		//httpclient.removeRequestInterceptorByClass(RequestTargetAuthentication.class);
		long s = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			HttpGet httpget = new HttpGet(
					"http://10.232.43.8:8000/qp?usernid=-1&rqtest=dynamic&s=mall&c=2&src=tmall-search_10.13.134.19&k=pp&rc=2&nopt=1");
			HttpResponse response;
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			byte[] bytes = IOUtils.toByteArray(is);
			is.close();
			httpget.abort();
		}
		System.out.println(System.currentTimeMillis() - s);
	}
}
