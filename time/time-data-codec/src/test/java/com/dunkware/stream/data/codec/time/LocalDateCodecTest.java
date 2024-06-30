package com.dunkware.stream.data.codec.time;


import org.junit.Test;

import com.dunkware.time.data.codec.time.LocalDateCodec;

import junit.framework.TestCase;

import java.time.LocalDate;


public class LocalDateCodecTest extends  TestCase {



    public void testCodec() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        long serialized = LocalDateCodec.encode(date);
        LocalDate back = LocalDateCodec.decode(serialized);
        assertFalse(back.isAfter(date));
        assertFalse(back.isBefore(date));
        assertTrue(back.isEqual(date));
    }


}
