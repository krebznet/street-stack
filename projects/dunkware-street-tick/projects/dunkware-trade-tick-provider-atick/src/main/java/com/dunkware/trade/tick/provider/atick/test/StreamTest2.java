package com.dunkware.trade.tick.provider.atick.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StreamTest2 {
	
	public static void main(String[] args) {
		try {
			test();	
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

	static  void test() throws IOException {
		try {
			HttpsURLConnection c = (HttpsURLConnection) new URL("https://api.activetick.com/stream.json?sessionid=12225759b81b421a9ce69db19c9222d8&symbols=AAPL_S-U,IBM_S-U")
					.openConnection();
			c.setRequestMethod("POST");
			c.setRequestProperty("Connection", "Keep-Alive");
			c.setRequestProperty("Accept-Encoding", "application/json");
			
			InputStream io = c.getInputStream();
			
			BufferedInputStream bis = new BufferedInputStream(io);
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			for (int result = bis.read(); result != -1; result = bis.read()) {
			    buf.write((byte) result);
			}		
		} catch (Exception e) {
			System.err.println("here");
		}
	
		
	}

}
