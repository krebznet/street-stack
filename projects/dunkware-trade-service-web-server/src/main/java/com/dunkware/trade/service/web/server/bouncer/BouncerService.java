package com.dunkware.trade.service.web.server.bouncer;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Service
public class BouncerService {

	@Value("${bouncer.username}")
	private String username;

	@Value("${bouncer.password}")
	private String password;

	@Value("${bouncer.host}")
	private String host;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Marker marker = MarkerFactory.getMarker("web.bouncer.service");

	
	
	public void bounce() {

		Session session = null;
		ChannelExec channel = null;

		try {
			logger.info(marker, "Bounce Executing " + host + " " + password + " " + username);
			session = new JSch().getSession(username, host, 22);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand("/home/mradmin/k8s/street/cloud/bounce.sh");
			
			ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
			channel.setOutputStream(responseStream);
			channel.connect();

			while (channel.isConnected()) {
				Thread.sleep(100);
			}

			String responseString = new String(responseStream.toByteArray());
			
			System.out.println(responseString);

		} catch (Exception e) {
			logger.error(marker, "Bouncer destory exception " + e.toString(),e);

		} finally {
			if (session != null) {
				session.disconnect();
			}
			if (channel != null) {
				channel.disconnect();
			}
		}

	}

}
