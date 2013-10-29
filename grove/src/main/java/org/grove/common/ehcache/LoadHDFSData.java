package org.grove.common.ehcache;


public class LoadHDFSData {

	/*public static void main(String[] args) throws Exception{
		
		CacheManager cacheManager = new CacheManager();
		Ehcache cache = cacheManager.getCache("zymo");
		
		String uri = "hdfs://a639.zymo.weibo.com:9000/";
		
		Configuration conf = new Configuration();
		
		FileSystem hdfs = FileSystem.get(URI.create(uri),conf);
		
		Path rootPath = new Path("/zymo/zymo/20120229");
		
		FileStatus[] fs = hdfs.listStatus(rootPath);
		
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		String tmp ;
		
		int j = 0;
		for(FileStatus fileStatus : fs){
			is = hdfs.open(fileStatus.getPath());
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			System.out.println(fileStatus.getPath().getName());
			System.out.println(cache.getMemoryStoreSize()/1024 + " : " + cache.getSize());
			while((tmp=br.readLine())!=null){
				String[] be = tmp.split("\t\t");
				if(be.length>1){
					String[] basicField = be[0].split("\t");
					String cacheKey = basicField[2] + basicField[3] +"_"+ basicField[4] + basicField[5];
					Element elem = cache.get("cacheKey");
				    if(elem == null){
				    	cache.put(new Element(cacheKey, null));
				    }
				}
			}
			br.close();
			isr.close();
			is.close();
			j++;
			if(j>130){
				break;
			}
		}
		
		
		File localLog = new File("/data2/logtmp/");

		File[] logFiles = localLog.listFiles();
		
		FileInputStream fis ;
		
		int mark = 0;
		
		long startTime = System.currentTimeMillis();
		
		for(File log : logFiles){
			System.out.println(log.getAbsolutePath());
			fis = new FileInputStream(log);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while((tmp=br.readLine())!=null){
				String[] be = tmp.split("\t\t");
				if(be.length>1){
					String[] basicField = be[0].split("\t");
					String cacheKey = basicField[2] + basicField[3] +"_"+ basicField[4] + basicField[5];
					Element elem = cache.get("cacheKey");
				    if(elem == null){
				    	cache.put(new Element(cacheKey, null));
				    }else{
				    	mark++;
				    }
				}
			}
			br.close();
			isr.close();
			fis.close();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("time : "+ (endTime - startTime));
		
		System.out.println(mark++);
		
		System.out.println(cache.getSize());
		
		cacheManager.shutdown();
	}*/
}
