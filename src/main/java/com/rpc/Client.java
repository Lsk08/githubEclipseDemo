package com.rpc;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientBossPool;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class Client {
	
	public static void main(String[] args) {
		//客户端
		ClientBootstrap clientBootstrap=new ClientBootstrap();
		
		//boss线程 和 work线程
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		//socket工厂
		clientBootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));
	
		//管道工厂
		clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("hiHandler", new HiHandler());
				return pipeline;
			}
			
		});
		
		ChannelFuture future = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));
		Channel channel = future.getChannel();
		
		System.out.println("start!!");
		
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("输入");
			channel.write(sc.nextLine());
		}
		
		
		
	
	
	}

}
