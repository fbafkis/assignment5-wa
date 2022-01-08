package com.francescobertamini.assignment5.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="HOTELS")

public class Hotel {

    @Id
    @Column (name = "ID")
    public Long id;
    @Basic
    @Column (name = "HALF_BOARD_PRICE", nullable = false)
    public int halfBoardPrice;
    @Basic
    @Column (name = "STARS", nullable = false)
    public int stars;
    @Basic
    @Column (name = "MAX_PERSONS", nullable = false)
    public int maxPersons;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID")
    private Accommodation accommodation;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<HotelsBooking> hotelsBookings;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<HotelsDay> hotelsDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public int getHalfBoardPrice() {
        return halfBoardPrice;
    }

    public void setHalfBoardPrice(int halfBoardPrice) {
        this.halfBoardPrice = halfBoardPrice;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }
}
