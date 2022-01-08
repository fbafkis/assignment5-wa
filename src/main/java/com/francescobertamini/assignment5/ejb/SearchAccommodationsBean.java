package com.francescobertamini.assignment5.ejb;

import com.francescobertamini.assignment5.entities.Accommodation;
import com.francescobertamini.assignment5.entities.Apartment;
import com.francescobertamini.assignment5.entities.Hotel;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;

@Stateless
@Local

public class SearchAccommodationsBean implements SearchAccommodations {
    @PersistenceContext(unitName = "ASS5DB")
    EntityManager entityManager;

    @Override
    public ArrayList<Apartment> searchAvailableApartments(LocalDate arrival, LocalDate departure, long days, int personsNumber) {

        ArrayList<Apartment> result = new ArrayList<>();
        ArrayList<Apartment> result1 = new ArrayList<>();

        Query q = entityManager.createQuery("From Apartment apartment where" +
                " apartment.maxPersons>=\'" + personsNumber + "\'");
        result1 = (ArrayList<Apartment>) q.getResultList();

        if (!result1.isEmpty()) {
            for (int a = 0; a < result1.size(); a++) {
                boolean free = true;
                for (int d = 0; d < days; d++) {
                    if (free != false) {
                        Long dayResult;
                        Query q2 = entityManager.createQuery("select count(*) from ApartmentsDay " +
                                "apartmentsDay where apartmentsDay.daydate=\'" + arrival.plusDays(d) +
                                "\' and apartmentsDay.apartmentId=" + result1.get(a).getId() +
                                " and apartmentsDay.occupied=true");
                        dayResult = (Long) q2.getSingleResult();
                        if (dayResult > 0) {
                            free = false;
                        }
                    }
                }
                if (free == true) {
                    result.add(result1.get(a));
                }
            }
        }
        return result;
    }

    @Override
    public ArrayList<Hotel> searchAvailableHotels(LocalDate arrival, LocalDate departure, long days, int personsNumber) {

        ArrayList<Hotel> result = new ArrayList<>();
        ArrayList<Hotel> result1 = new ArrayList<>();

        Query q = entityManager.createQuery("From Hotel hotel where" +
                " hotel.maxPersons>=\'" + personsNumber + "\'");
        result1 = (ArrayList<Hotel>) q.getResultList();

        if (!result1.isEmpty()) {

            for (int a = 0; a < result1.size(); a++) {

                boolean free = true;

                for (int d = 0; d < days; d++) {
                    if (free != false) {
                        Long dayResult;

                        Query q2 = entityManager.createQuery("select count(*) from HotelsDay " +
                                "hotelsdDay where hotelsdDay.daydate=\'" + arrival.plusDays(d) +
                                "\' and hotelsdDay.hotelId=" + result1.get(a).getId() +
                                " and hotelsdDay.personsNumber + " + personsNumber
                                + " > " + result1.get(a).getMaxPersons());
                        dayResult = (Long) q2.getSingleResult();
                        if (dayResult > 0) {
                            free = false;
                        }
                    }
                }
                if (free == true) {
                    result.add(result1.get(a));
                }
            }
        }
        return result;
    }

    @Override
    public Accommodation getAccommodationById(Long id) {

        Accommodation result;

        try {
            Query q = entityManager.createQuery("From Accommodation accommodation where id=" + id);
            result = (Accommodation) q.getSingleResult();
        } catch (Exception e) {
            result = null;
            System.err.println(e);
        }
        return result;
    }

    @Override
    public Apartment getApartmentById(Long id) {

        Apartment result;

        try {
            Query q = entityManager.createQuery("From Apartment apartment where id=" + id);
            result = (Apartment) q.getSingleResult();
        } catch (Exception e) {
            result = null;
            System.err.println(e);
        }
        return result;
    }


    @Override
    public Hotel getHotelById(Long id) {

        Hotel result;

        try {
            Query q = entityManager.createQuery("From Hotel hotel where id=" + id);
            result = (Hotel) q.getSingleResult();
        } catch (Exception e) {
            result = null;
            System.err.println(e);
        }
        return result;
    }
}
