package com.dunkware.learn.server.channel;

import com.dunkware.spring.channel.Channel;
import com.dunkware.spring.channel.anot.AChannelHandler;
import com.dunkware.spring.channel.anot.AChannelSetter;
import com.dunkware.spring.channel.anot.AMessageHandler;

@AChannelHandler("ClientChannel")
public class ClientChannel {
	
	
	private Channel channel; 
	
	@AMessageHandler()
	public void recieveClientMessage(ClientMessage message) { 
		System.out.println("receieved server message " + message.getValue());
	}
	
	
	@AChannelSetter()
	public void setChannel(Channel channel) { 
		this.channel = channel; 
	}
	
	

}
