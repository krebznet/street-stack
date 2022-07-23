package com.dunkware.learn.server.channel;

import com.dunkware.spring.channel.Channel;
import com.dunkware.spring.channel.anot.AChannelHandler;
import com.dunkware.spring.channel.anot.AChannelSetter;
import com.dunkware.spring.channel.anot.AMessageHandler;
import com.dunkware.spring.channel.anot.AMessageReply;

@AChannelHandler("ServerChannel")
public class ServerChannel {
	
	
	private Channel channel; 
	
	@AMessageHandler()
	public void recieveClientMessage(ServerMessage message) { 
		System.out.println("receieved client message " + message.getValue());
	}
	
	
	@AChannelSetter()
	public void setChannel(Channel channel) { 
		this.channel = channel; 
	}
	
	
	@AMessageReply()
	public ClientMessage requestReply(ServerMessage message) { 
		ClientMessage m = new ClientMessage();
		m.setValue(message.getValue());;
		return m;
	}
	
	

}
