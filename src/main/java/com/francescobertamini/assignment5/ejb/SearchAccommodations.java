package com.francescobertamini.assignment5.ejb;

import com.francescobertamini.assignment5.entities.Accommodation;
import com.francescobertamini.assignment5.entities.Apartment;
import com.francescobertamini.assignment5.entities.Hotel;

import java.time.LocalDate;
import java.util.ArrayList;

public interface SearchAccommodations {

    ArrayList<Apartment> searchAvailableApartments(LocalDate arrival, LocalDate departure, long days, int personsNumber);

    ArrayList<Hotel> searchAvailableHotels(LocalDate arrival, LocalDate departure, long days, int personsNumber);

    Accommodation getAccommodationById(Long id);

    Apartment getApartmentById(Long id);

    Hotel getHotelById(Long id);

}
