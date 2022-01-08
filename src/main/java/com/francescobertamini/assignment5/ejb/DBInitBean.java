package com.francescobertamini.assignment5.ejb;

import com.francescobertamini.assignment5.entities.Accommodation;
import com.francescobertamini.assignment5.entities.Apartment;
import com.francescobertamini.assignment5.entities.Hotel;

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
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Stateless
@Local
public class DBInitBean implements DBInit {

    @PersistenceContext(unitName = "ASS5DB")
    private EntityManager entityManager;

    Accommodation accommodation1 = new Accommodation();
    Accommodation accommodation2 = new Accommodation();
    Accommodation accommodation3 = new Accommodation();
    Accommodation accommodation4 = new Accommodation();
    Accommodation accommodation5 = new Accommodation();
    Accommodation accommodation6 = new Accommodation();
    Accommodation accommodation7 = new Accommodation();
    Apartment apartment1 = new Apartment();
    Apartment apartment2 = new Apartment();
    Apartment apartment3 = new Apartment();
    Hotel hotel1 = new Hotel();
    Hotel hotel2 = new Hotel();
    Hotel hotel3 = new Hotel();
    Hotel hotel4 = new Hotel();

    @Override
    public void emptyDB() {

        Query emptyHotelsDays = entityManager.createQuery("DELETE FROM HotelsDay ");
        Query emptyHotelsBookings = entityManager.createQuery("DELETE FROM HotelsBooking ");
        Query emptyHotels = entityManager.createQuery("DELETE FROM Hotel ");
        Query emptyApartmentsDays = entityManager.createQuery("DELETE FROM ApartmentsDay");
        Query emptyApartmentsBookings = entityManager.createQuery("DELETE FROM ApartmentsBooking ");
        Query emptyApartments = entityManager.createQuery("DELETE FROM Apartment");
        Query emptyAccommodations = entityManager.createQuery("DELETE FROM Accommodation ");

        try {
            emptyHotelsDays.executeUpdate();
            emptyHotelsBookings.executeUpdate();
            emptyHotels.executeUpdate();
            emptyApartmentsDays.executeUpdate();
            emptyApartmentsBookings.executeUpdate();
            emptyApartments.executeUpdate();
            emptyAccommodations.executeUpdate();
            entityManager.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void insertAccommodations() {

        ArrayList<Apartment> apartments = new ArrayList<>();
        ArrayList<Hotel> hotels = new ArrayList<>();

        accommodation1.setName("Pietra Bianca");
        accommodation1.setPrice(40);
        accommodation1.setType(0);
        this.entityManager.persist(accommodation1);
        apartment1.setAccommodation(accommodation1);
        apartment1.setId(accommodation1.getId());
        apartment1.setCleaningPrice(15);
        apartment1.setMaxPersons(4);
        this.entityManager.persist(apartment1);
        apartments.add(apartment1);

        accommodation2.setName("Sapore Di Sale");
        accommodation2.setPrice(80);
        accommodation2.setType(0);
        this.entityManager.persist(accommodation2);
        apartment2.setAccommodation(accommodation2);
        apartment2.setId(accommodation2.getId());
        apartment2.setCleaningPrice(20);
        apartment2.setMaxPersons(8);
        this.entityManager.persist(apartment2);
        apartments.add(apartment2);

        accommodation3.setName("Tenuta Di Artimino");
        accommodation3.setPrice(60);
        accommodation3.setType(0);
        this.entityManager.persist(accommodation3);
        apartment3.setAccommodation(accommodation3);
        apartment3.setId(accommodation3.getId());
        apartment3.setCleaningPrice(12);
        apartment3.setMaxPersons(6);
        this.entityManager.persist(apartment3);
        apartments.add(apartment3);

        accommodation4.setName("Artemide");
        accommodation4.setPrice(100);
        accommodation4.setType(1);
        this.entityManager.persist(accommodation4);
        hotel1.setAccommodation(accommodation4);
        hotel1.setId(accommodation4.getId());
        hotel1.setHalfBoardPrice(20);
        hotel1.setStars(4);
        hotel1.setMaxPersons(60);
        this.entityManager.persist(hotel1);
        hotels.add(hotel1);

        accommodation5.setName("Majestic");
        accommodation5.setPrice(65);
        accommodation5.setType(1);
        this.entityManager.persist(accommodation5);
        hotel2.setAccommodation(accommodation5);
        hotel2.setId(accommodation5.getId());
        hotel2.setHalfBoardPrice(15);
        hotel2.setStars(3);
        hotel2.setMaxPersons(50);
        this.entityManager.persist(hotel2);
        hotels.add(hotel2);

        accommodation6.setName("Palace");
        accommodation6.setPrice(200);
        accommodation6.setType(1);
        this.entityManager.persist(accommodation6);
        hotel3.setAccommodation(accommodation6);
        hotel3.setId(accommodation6.getId());
        hotel3.setHalfBoardPrice(30);
        hotel3.setStars(5);
        hotel3.setMaxPersons(25);
        this.entityManager.persist(hotel3);
        hotels.add(hotel3);

        accommodation7.setName("Zenith");
        accommodation7.setPrice(70);
        accommodation7.setType(1);
        this.entityManager.persist(accommodation7);
        hotel4.setAccommodation(accommodation7);
        hotel4.setId(accommodation7.getId());
        hotel4.setHalfBoardPrice(18);
        hotel4.setStars(3);
        hotel4.setMaxPersons(40);
        this.entityManager.persist(hotel4);
        hotels.add(hotel4);

        LocalDate start = LocalDate.of(2021, 02, 01);
        LocalDate end = LocalDate.of(2021, 02, 28);
        long days = start.until(end, ChronoUnit.DAYS);

        ////// Apartments bookings

        Context ctx = null;
        BookApartment bookApartment = null;
        try {
            ctx = new InitialContext();
            String name = "java:module/BookApartmentBean!com.francescobertamini.assignment5.ejb.BookApartment";
            bookApartment = (BookApartment) ctx.lookup(name);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        for (int a = 0; a < apartments.size(); a++) {
            ArrayList<LocalDate> usedDates = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                LocalDate date;
                do {
                    long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
                    date = start.plusDays(randomDays);
                } while (usedDates.contains(date));
                usedDates.add(date);
                bookApartment.addBooking(apartments.get(a).getId(), "Mock Guest", date, date.plusDays(1));
            }
        }

        ////// Hotels bookings

        BookHotel bookHotel = null;

        try {
            ctx = new InitialContext();
            String name = "java:module/BookHotelBean!com.francescobertamini.assignment5.ejb.BookHotel";
            bookHotel = (BookHotel) ctx.lookup(name);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        double minPercentage = 0.90;
        double maxPercentage = 1.00;

        for (int h = 0; h < hotels.size(); h++) {
            for (int d = 0; d < days; d++) {
                Random r = new Random();
                double randomPercentage = minPercentage + (maxPercentage - minPercentage) * r.nextDouble();
                int randomPersonsNumber = (int) Math.round(hotels.get(h).getMaxPersons() * randomPercentage);
                boolean halfBoard = ThreadLocalRandom.current().nextBoolean();
                bookHotel.addBooking(hotels.get(h).getId(), "Mock Guest", start.plusDays(d), start.plusDays(d + 1), randomPersonsNumber, halfBoard);
            }
        }
    }
}
