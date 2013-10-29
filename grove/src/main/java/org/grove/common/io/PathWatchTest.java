package org.grove.common.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.junit.Test;
/**
 * jdk7的文件监控
 * @author xiaolin.mxl
 *
 */
public class PathWatchTest {

	Path path = Paths.get("E:/xxx");
	
	@Test
	public void watchPathModify()throws Exception{
		WatchService service = FileSystems.getDefault().newWatchService();
		path.register(service,StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
		
		 while(true)
	       {
	          WatchKey key = service.take();	// retrieve the watchkey
	          for (WatchEvent event : key.pollEvents())
	          {
	             System.out.println(event.kind() + ": "+ event.context());	// Display event and file name
	          }
	          boolean valid = key.reset();
	          if (!valid)
	          {
	             break;
	          }
	       }
	}
}
