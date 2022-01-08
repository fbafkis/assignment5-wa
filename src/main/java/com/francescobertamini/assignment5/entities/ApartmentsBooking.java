package com.francescobertamini.assignment5.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "APARTMENTS_BOOKINGS")

public class ApartmentsBooking implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name="ID")
    public Long id;

    @Basic
    @Column(name = "GUEST_NAME", nullable = false)
    public String guestName;
    @Column(name = "ARRIVAL", nullable = false)
    public LocalDate arrival;
    @Column(name = "DEPARTURE", nullable = false)
    public LocalDate departure;
    @Basic
    @Column(name = "DAYS", nullable = false)
    public int days;

    @ManyToOne
    private Apartment apartment;


    public Long getBookingId() {
        return id;
    }

    public void setBookingId(Long id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
