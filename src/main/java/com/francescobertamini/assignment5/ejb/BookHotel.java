package com.francescobertamini.assignment5.ejb;

import java.time.LocalDate;

public interface BookHotel {

    void addBooking(Long hotelId, String guestName, LocalDate arrival, LocalDate departure, int personsNumber, boolean halfBoard);

}
