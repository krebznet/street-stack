package com.dunkware.stream.data.codec.time;

import java.time.LocalTime;

public class LocalTimeCodec {

    public static long encode(LocalTime time) {
        return time.toNanoOfDay();
    }

    public static LocalTime decode(long input) {
        return LocalTime.ofNanoOfDay(input);
    }
}
