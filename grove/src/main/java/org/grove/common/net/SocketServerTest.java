package org.grove.common.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {

	public static void main(String[] args) throws Exception {
		ServerSocket s = new ServerSocket(9000);

		Socket inconing = s.accept();// 等待连接，该方法阻塞

		InputStream is = inconing.getInputStream();
		OutputStream os = inconing.getOutputStream();

		boolean done = false;

		/*while (!done) {
			String line = in.nextLine();
			out.println("echo :" + line);
			if (line.trim().equals("bye")) {
				done = true;
			}
		}*/
		inconing.close();
	}

}
