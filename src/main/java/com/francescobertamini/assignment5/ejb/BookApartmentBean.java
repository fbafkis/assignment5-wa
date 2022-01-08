package com.francescobertamini.assignment5.ejb;

import com.francescobertamini.assignment5.entities.Apartment;
import com.francescobertamini.assignment5.entities.ApartmentsBooking;
import com.francescobertamini.assignment5.entities.ApartmentsDay;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Stateless
@Local
public class BookApartmentBean implements BookApartment {

    @PersistenceContext(unitName = "ASS5DB")
    EntityManager entityManager;

    @Override
    @Transactional
    public void addBooking(Long apartmentId, String guestName, LocalDate arrival, LocalDate departure) {
        Context ctx;
        SearchAccommodations searchAccommodations = null;
        try {
            ctx = new InitialContext();
            String name = "java:module/SearchAccommodationsBean!com.francescobertamini.assignment5.ejb.SearchAccommodations";
            searchAccommodations = (SearchAccommodations) ctx.lookup(name);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        ApartmentsBooking booking = new ApartmentsBooking();
        Apartment apartment = searchAccommodations.getApartmentById(apartmentId);

        booking.setApartment(apartment);
        booking.setArrival(arrival);
        booking.setDeparture(departure);
        booking.setGuestName(guestName);
        int duration = (int) arrival.until(departure, ChronoUnit.DAYS);
        booking.setDays(duration);

        ArrayList<ApartmentsDay> daysArray = new ArrayList<>();

        for (int i = 0; i < duration; i++) {
            ApartmentsDay apartmentsDay = new ApartmentsDay();
            apartmentsDay.setDaydate(arrival.plusDays(i));
            apartmentsDay.setApartmentId(apartmentId);
            apartmentsDay.setApartment(apartment);
            apartmentsDay.setOccupied(true);
            daysArray.add(apartmentsDay);
        }

        entityManager.persist(booking);

        for (int e = 0; e < daysArray.size(); e++) {
            entityManager.persist(daysArray.get(e));

        }
    }
}
