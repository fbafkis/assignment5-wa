package com.francescobertamini.assignment5.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "APARTMENTS")

public class Apartment {

    @Id
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "CLEANING_PRICE", nullable = false)
    public int cleaningPrice;
    @Basic
    @Column(name = "MAX_PERSONS", nullable = false)
    public int maxPersons;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID")
    private Accommodation accommodation;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private Set<ApartmentsBooking> apartmentsBookings;
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private Set<ApartmentsDay> apartmentsDays;

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCleaningPrice() {
        return cleaningPrice;
    }

    public void setCleaningPrice(int cleaningPrice) {
        this.cleaningPrice = cleaningPrice;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }
}
