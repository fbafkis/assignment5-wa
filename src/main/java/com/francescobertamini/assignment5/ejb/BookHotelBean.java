package com.francescobertamini.assignment5.ejb;

import com.francescobertamini.assignment5.entities.Hotel;
import com.francescobertamini.assignment5.entities.HotelsBooking;
import com.francescobertamini.assignment5.entities.HotelsDay;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Stateless
@Local
public class BookHotelBean implements BookHotel {

    @PersistenceContext(unitName = "ASS5DB")
    EntityManager entityManager;

    @Override
    @Transactional
    public void addBooking(Long hotelId, String guestName, LocalDate arrival, LocalDate departure, int personsNumber, boolean halfBoard) {

        Context ctx;
        SearchAccommodations searchAccommodations = null;
        try {
            ctx = new InitialContext();
            String name = "java:module/SearchAccommodationsBean!com.francescobertamini.assignment5.ejb.SearchAccommodations";
            searchAccommodations = (SearchAccommodations) ctx.lookup(name);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        HotelsBooking booking = new HotelsBooking();
        Hotel hotel = searchAccommodations.getHotelById(hotelId);

        booking.setHotel(hotel);
        booking.setArrival(arrival);
        booking.setDeparture(departure);
        booking.setGuestName(guestName);
        booking.setHalfBoard(halfBoard);
        booking.setPersonsNumber(personsNumber);
        int duration = (int) arrival.until(departure, ChronoUnit.DAYS);
        booking.setDays(duration);
        entityManager.persist(booking);

        for (int i = 0; i < duration; i++) {
            HotelsDay result;

            try {
                Query q = entityManager.createQuery("From HotelsDay hotelsDay where " +
                        "hotelsDay.hotelId=" + hotelId + " and hotelsDay.daydate=\'" +
                        arrival.plusDays(i) + "\'");
                result = (HotelsDay) q.getSingleResult();
            } catch (Exception e) {
                System.out.println("HotelsDay not found.");
                result = null;
            }

            if (result == null) {
                HotelsDay hotelsDay = new HotelsDay();
                hotelsDay.setDaydate(arrival.plusDays(i));
                hotelsDay.setHotelId(hotelId);
                hotelsDay.setHotel(hotel);
                hotelsDay.setPersonsNumber(personsNumber);
                entityManager.persist(hotelsDay);
            } else {
                result.setPersonsNumber(result.getPersonsNumber() + personsNumber);
                entityManager.persist(result);

            }
        }
    }
}
