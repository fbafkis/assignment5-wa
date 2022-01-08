package com.francescobertamini.assignment5.ejb;

import com.francescobertamini.assignment5.entities.ApartmentsBooking;
import com.francescobertamini.assignment5.entities.HotelsBooking;

import java.util.ArrayList;

public interface SearchBookings {

    ArrayList<ApartmentsBooking> searchApartmentBookings(String guestName);

    ArrayList<HotelsBooking> searchHotelBookings(String guestName);

}
