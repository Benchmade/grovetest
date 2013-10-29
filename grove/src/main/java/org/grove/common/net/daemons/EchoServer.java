package org.grove.common.net.daemons;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class EchoServer {
	private int port = 8000;
	private ServerSocket serverSocket;
	private ExecutorService executorService; // 线程池
	private final int POOL_SIZE = 4; // 单个CPU时线程池中工作线程的数目
	private int portForShutdown = 8001; // 用于监听关闭服务器命令的端口
	private ServerSocket shutdownListener;
	private boolean isShutdown = false; // 服务器是否已经关闭

	public EchoServer() throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(60000); // 设定等待客户连接的超过时间为60秒
		shutdownListener = new ServerSocket(portForShutdown);
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);// 创建线程池
		shutdownThread.start(); // 启动负责关闭服务器的线程
		System.out.println("服务器启动");
	}
	
	private Thread shutdownThread = new Thread() { // 负责关闭服务器的线程
		public void start() {
			this.setDaemon(true); // 设置为守护线程（也称为后台线程）
			super.start();
		}
		public void run() {
			while (!isShutdown) {
				Socket socketShutdown = null;
				try {
					socketShutdown = shutdownListener.accept();
					BufferedReader br = new BufferedReader(new InputStreamReader(socketShutdown.getInputStream()));
					String command = br.readLine();
					if (command.equals("shutdown")) {
						long beginTime = System.currentTimeMillis();
						socketShutdown.getOutputStream().write("服务器正在关闭\r\n".getBytes());
						isShutdown = true;
						executorService.shutdown();// 请求关闭线程池  线程池不再接收新的任务，但是会继续执行完工作队列中现有的任务
						while (!executorService.isTerminated()){// 等待关闭线程池，每次等待的超时时间为30秒
							executorService.awaitTermination(30,TimeUnit.SECONDS);
						}
						serverSocket.close(); // 关闭与EchoClient客户通信的ServerSocket
						long endTime = System.currentTimeMillis();
						socketShutdown.getOutputStream().write(("服务器已经关闭，" + "关闭服务器用了" + (endTime - beginTime) + "毫秒\r\n").getBytes());
						socketShutdown.close();
						shutdownListener.close();
					} else {
						socketShutdown.getOutputStream().write("错误的命令\r\n".getBytes());
						socketShutdown.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};

	public void service() {
		while (!isShutdown) {
			Socket socket = null;
			try {
				socket = serverSocket.accept(); // 可能会抛出SocketTimeoutException和SocketException
				socket.setSoTimeout(60000); // 把等待客户发送数据的超时时间设为60秒
				executorService.execute(new Handler(socket)); // 可能会抛出RejectedExecutionException
			} catch (SocketTimeoutException e) {// 不必处理等待客户连接时出现的超时异常
			} catch (RejectedExecutionException e) {
				try {
					if (socket != null){
						socket.close();
					}
				}catch (IOException x) {
				}
				return;
			} catch (SocketException e) {
				if (e.getMessage().indexOf("socket closed") != -1)// 如果是由于在执行serverSocket.accept()方法时，ServerSocket被ShutdownThread线程关闭而导致的异常，就退出service()方法
					return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]) throws IOException {
		new EchoServer().service();
	}
}

class Handler implements Runnable {
	private Socket socket;
	public Handler(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		try {
			System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
			BufferedReader br = getReader(socket);
			PrintWriter pw = getWriter(socket);
			String msg = null;
			while ((msg = br.readLine()) != null) {
				System.out.println(msg);
				pw.println("echo:" + msg);
				if (msg.equals("bye"))break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null){
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}
	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}
}

/****************************************************
 * 作者：孙卫琴 * 来源：<<Java网络编程精解>> * 技术支持网址：www.javathinker.org *
 ***************************************************/
