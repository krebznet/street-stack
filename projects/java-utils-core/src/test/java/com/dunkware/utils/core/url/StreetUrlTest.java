package com.dunkware.utils.core.url;

import org.junit.Test;

import com.dunkware.utils.core.utl.DunkURLBuilder;

import junit.framework.TestCase;

public class StreetUrlTest extends TestCase {

	@Test
	public void test1() {
		String url = "http://www.moneystash.com:323/query?amount=32.3";
		String built = DunkURLBuilder.builder("http", "www.moneystash.com", 323, "query").param("amount", 32.3)
				.build();
		System.out.println(built);
		assertEquals(url, built);
		built = DunkURLBuilder.builder("http", "www.moneystash.com", 323, "/query/").param("amount", 32.3).build();
		System.out.println(built);
		assertEquals(url, built);
		String paths = DunkURLBuilder.builder("http://feed.dunkware.net").path("/echo").param("say", "hello").build();

		assertEquals("http://feed.dunkware.net/echo?say=hello", paths);
		paths = DunkURLBuilder.builder("http://feed.dunkware.net").path("/echo").path("test").param("say", "hello").build();
		System.out.println(paths);
		assertEquals("http://feed.dunkware.net/echo/test?say=hello", paths);
	}

}
