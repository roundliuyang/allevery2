package com.yly.server;

import com.yly.api.Booking;
import com.yly.api.BookingException;
import com.yly.api.CabBookingService;

import static java.lang.Math.random;
import static java.util.UUID.randomUUID;

public class CabBookingServiceImpl implements CabBookingService {
    @Override
    public Booking bookRide(String pickUpLocation) throws BookingException {
        if (random() < 0.3) {
            throw new BookingException("Cab unavailable");
        }
        return new Booking(randomUUID().toString());
    }
}
