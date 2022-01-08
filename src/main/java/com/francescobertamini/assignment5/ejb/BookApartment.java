package com.francescobertamini.assignment5.ejb;

import java.time.LocalDate;

public interface BookApartment {

    void addBooking(Long apartmentId, String guestName, LocalDate arrival, LocalDate departure);

}
