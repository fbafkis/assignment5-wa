package com.francescobertamini.assignment5.ejb;

import com.francescobertamini.assignment5.entities.ApartmentsBooking;
import com.francescobertamini.assignment5.entities.HotelsBooking;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;

@Stateless
@Local

public class SearchBookingsBean implements SearchBookings {
    @PersistenceContext(unitName = "ASS5DB")
    EntityManager entityManager;

    @Override
    public ArrayList<ApartmentsBooking> searchApartmentBookings(String guestName) {

        ArrayList<ApartmentsBooking> result = new ArrayList<>();

        Query q = entityManager.createQuery("From ApartmentsBooking apartmentsBooking where" +
                " apartmentsBooking.guestName=\'" + guestName + "\'");
        result = (ArrayList<ApartmentsBooking>) q.getResultList();
        return result;
    }

    @Override
    public ArrayList<HotelsBooking> searchHotelBookings(String guestName) {

        ArrayList<HotelsBooking> result = new ArrayList<>();
        Query q = entityManager.createQuery("From HotelsBooking HotelsBooking where" +
                " HotelsBooking.guestName=\'" + guestName + "\'");
        result = (ArrayList<HotelsBooking>) q.getResultList();
        return result;
    }
}
