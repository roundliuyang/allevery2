package com.yly.api;

import java.io.Serializable;

import static java.lang.String.format;

/**
 * todo 此处为什么要实现序列化接口呢
 */
public class Booking implements Serializable {
    private String bookingCode;

    @Override public String toString() {
        return format("Ride confirmed: code '%s'.", bookingCode);
    }

    public Booking(String bookingCode) {
        this.bookingCode = bookingCode;
    }
}
