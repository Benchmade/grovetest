package org.grove.common.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
/**
 * 测试失败,优化无效
 * @author xiaolin.mxl
 *
 */
public class BenchMark {

	byte[] data;
	
	@Before
	public void init() throws IOException{
		data = Files.readAllBytes(Paths.get("C:/Users/xiaolin.mxl/Desktop/engine"));
	} 
	/**
	 * 1.3x x>2 x<4
	 */
	@Test
	public void tmallOld(){
		SearchResultV3Parser p = new SearchResultV3Parser();
		long s = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			BufferParser parser = new BufferParser(data, null);
			p.parser(parser);
		}
		System.out.println(System.currentTimeMillis()-s);
	}
	
}
