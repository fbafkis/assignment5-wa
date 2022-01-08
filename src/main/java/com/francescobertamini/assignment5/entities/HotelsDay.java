package com.francescobertamini.assignment5.entities;

import com.francescobertamini.assignment5.compositeKeys.HotelsDayId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(HotelsDayId.class)

@Table(name = "HOTELS_DAYS")

public class HotelsDay implements Serializable {

    @Id
    @Column(name = "HOTEL_ID", nullable = false)
    private Long hotelId;
    @Id
    @Column(name = "DAYDATE", nullable = false, columnDefinition="TIMESTAMP")
    public LocalDate daydate;
    @Basic
    @Column(name = "PERSONS_NUMBER", nullable = false)
    public int personsNumber;

    @MapsId("hotelId")
    @JoinColumns({
            @JoinColumn(name = "HOTEL_ID", referencedColumnName = "ID"),
    })
    @ManyToOne
    public Hotel hotel;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getDaydate() {
        return daydate;
    }

    public void setDaydate(LocalDate daydate) {
        this.daydate = daydate;
    }

    public int getPersonsNumber() {
        return personsNumber;
    }

    public void setPersonsNumber(int personsNumber) {
        this.personsNumber = personsNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
