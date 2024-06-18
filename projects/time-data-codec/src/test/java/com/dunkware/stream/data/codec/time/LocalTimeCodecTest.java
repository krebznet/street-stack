package com.dunkware.stream.data.codec.time;


import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalTime;


public class LocalTimeCodecTest extends TestCase {


    public void testCodec() {
        LocalTime time = LocalTime.of(8, 3, 9);
        long serialized = LocalTimeCodec.encode(time);
        LocalTime back = LocalTimeCodec.decode(serialized);
        assertEquals(time.getHour(), back.getHour());
        assertEquals(time.getMinute(), back.getMinute());
        assertEquals(time.getSecond(), back.getSecond());
        assertEquals(time, back);

    }


}
