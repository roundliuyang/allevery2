package com.yly.api;

public interface CabBookingService {
    Booking bookRide(String pickUpLocation) throws BookingException;
}
