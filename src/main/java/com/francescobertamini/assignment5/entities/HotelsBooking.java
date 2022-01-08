package com.francescobertamini.assignment5.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "HOTELS_BOOKINGS")

public class HotelsBooking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Long id;

    @Basic
    @Column(name = "GUEST_NAME", nullable = false)
    public String guestName;
    @Column(name = "ARRIVAL", nullable = false)
    public LocalDate arrival;
    @Column(name = "DEPARTURE", nullable = false)
    public LocalDate departure;
    @Basic
    @Column(name = "PERSONS_NUMBER", nullable = false)
    public int personsNumber;
    @Basic
    @Column(name = "DAYS", nullable = false)
    public int days;
    @Basic
    @Column(name = "HALF_BOARD")
    public boolean halfBoard;

    @ManyToOne
    private Hotel hotel;

    public long getBookingId() {
        return id;
    }

    public void setBookingId(long bookingId) {
        this.id = bookingId;
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

    public int getPersonsNumber() {
        return personsNumber;
    }

    public void setPersonsNumber(int personsNumber) {
        this.personsNumber = personsNumber;
    }

    public boolean isHalfBoard() {
        return halfBoard;
    }

    public void setHalfBoard(boolean halfBoard) {
        this.halfBoard = halfBoard;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
