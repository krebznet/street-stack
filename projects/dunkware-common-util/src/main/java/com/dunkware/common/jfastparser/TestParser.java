// Copyright Hugh Perkins 2012, hughperkins -at- gmail
//
// This Source Code Form is subject to the terms of the Mozilla Public License, 
// v. 2.0. If a copy of the MPL was not distributed with this file, You can 
// obtain one at http://mozilla.org/MPL/2.0/.

package com.dunkware.common.jfastparser;

import com.dunkware.common.jfastparser.ExceptionAssertions.BlastContainer;

import junit.framework.TestCase;

public class TestParser extends TestCase {
	
	
	public void test2() { 
		String input = "16611,1,5,3,71178.43,-1";
		final Parser parser = new Parser(input);
		int dateKey = parser.eatInt();
		parser.eatChar(',');
		int ent = parser.eatInt();
		parser.eatChar(',');
		int element = parser.eatInt();
		parser.eatChar(',');
		int stat = parser.eatInt();
		parser.eatChar(',');
		double value = parser.eatDouble();
		parser.eatChar(',');
		int time = parser.eatInt();
		assertEquals(dateKey, 16611);
		assertEquals(ent,1);
	}
	
	public void test() {
		String input = "123 56.4 12:3";
		final Parser parser = new Parser(input);
		assertEquals(123, parser.eatInt());
		ExceptionAssertions.assertException(new BlastContainer() {
			@Override
			public void test() throws Exception {
				parser.eatInt();
			}
		});
		ExceptionAssertions.assertException(new BlastContainer() {
			@Override
			public void test() throws Exception {
				parser.eatDouble();
			}
		});
		parser.eatWhitespace();
		assertEquals(56.4, parser.eatDouble());
		parser.eatWhitespace();
		assertEquals(12, parser.eatInt());
		ExceptionAssertions.assertException(new BlastContainer() {
			@Override
			public void test() throws Exception {
				parser.eatChar('.');
			}
		});
		parser.eatChar(':');
		assertEquals(3, parser.eatInt());
	}
}
