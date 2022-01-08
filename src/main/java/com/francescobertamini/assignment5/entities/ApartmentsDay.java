package com.francescobertamini.assignment5.entities;

import com.francescobertamini.assignment5.compositeKeys.ApartmentsDayId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(ApartmentsDayId.class)

@Table(name = "APARTMENTS_DAYS")

public class ApartmentsDay implements Serializable {

    @Id
    @Column(name = "APARTMENT_ID", nullable = false)
    private Long apartmentId;
    @Id
    @Column(name = "DAYDATE", nullable = false)
    private LocalDate daydate;
    @Basic
    @Column(name = "OCCUPIED", nullable = false)
    public boolean occupied;

    @MapsId("apartmentId")
    @JoinColumns({
            @JoinColumn(name = "APARTMENT_ID", referencedColumnName = "ID"),
    })
    @ManyToOne
    public Apartment apartment;

    public LocalDate getDaydate() {
        return daydate;
    }

    public void setDaydate(LocalDate daydate) {
        this.daydate = daydate;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
