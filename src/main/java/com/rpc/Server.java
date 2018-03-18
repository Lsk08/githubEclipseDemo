package com.rpc;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
/**
 * netty鏈嶅姟绔叆闂�
 * @author -鐞村吔-
 *
 */
public class Server {

	public static void main(String[] args) {

		//服务器
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		//boss线程 和 work线程
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		//-----------------
		//int maxThreads = ((ThreadPoolExecutor) boss).getMaximumPoolSize();
		//-----------------
				
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("helloHandler", new HelloHandler());
				return pipeline;
			}
		});

		
		bootstrap.bind(new InetSocketAddress(8080));
		
		System.out.println("start!!!");
		
	}

}
