package com.rpc;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
/**
 * 娑堟伅鎺ュ彈澶勭悊绫�
 * @author -鐞村吔-
 *
 */
public class HiHandler extends SimpleChannelHandler {

	/**
	 * 鎺ユ敹娑堟伅
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

		
		String s = (String) e.getMessage();
		System.out.println(s);
		
		//鍥炲啓鏁版嵁
		//ctx.getChannel().write("hi");
		super.messageReceived(ctx, e);
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		System.out.println("exceptionCaught");
		super.exceptionCaught(ctx, e);
	}


	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}


	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelDisconnected");
		super.channelDisconnected(ctx, e);
	}


	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}
}
