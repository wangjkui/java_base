package com.wjk.base.java.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketChannelTest {

	/**
	 * 客户端采用NIO实现
	 */
	public static void client(){
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		SocketChannel socketChannel = null;
		try{
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);//非阻塞式信道
			socketChannel.connect(new InetSocketAddress("10.10.195.115",8080));

			if(socketChannel.finishConnect()){
				int i=0;
				while(true)
				{
					TimeUnit.SECONDS.sleep(1);
					String info = "I'm "+i+++"-th information from client";
					buffer.clear();
					buffer.put(info.getBytes());
					buffer.flip();
					while(buffer.hasRemaining()){
						System.out.println(buffer);
						socketChannel.write(buffer);
					}
				}
			}
		}
		catch (IOException | InterruptedException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(socketChannel!=null){
					socketChannel.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 服务端依旧使用IO实现。
	 */
	public static void server(){
	       ServerSocket serverSocket = null;
	       InputStream in = null;
	       try
	       {
	           serverSocket = new ServerSocket(8080);
	           int recvMsgSize = 0;
	           byte[] recvBuf = new byte[1024];
	           while(true){
	               Socket clntSocket = serverSocket.accept();
	               SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
	               System.out.println("Handling client at "+clientAddress);
	               in = clntSocket.getInputStream();
	               while((recvMsgSize=in.read(recvBuf))!=-1){
	                   byte[] temp = new byte[recvMsgSize];
	                   System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
	                   System.out.println(new String(temp));
	               }
	           }
	       }
	       catch (IOException e)
	       {
	           e.printStackTrace();
	       }
	       finally{
	           try{
	               if(serverSocket!=null){
	                   serverSocket.close();
	               }
	               if(in!=null){
	                   in.close();
	               }
	           }catch(IOException e){
	               e.printStackTrace();
	           }
	       }
	   }
}
