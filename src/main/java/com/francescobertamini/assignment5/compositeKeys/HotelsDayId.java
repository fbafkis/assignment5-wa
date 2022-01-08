package com.francescobertamini.assignment5.compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class HotelsDayId implements Serializable {

    @Column(name = "DAYDATE", nullable = false)
    private LocalDate daydate;
    @Column(name = "HOTEL_ID", nullable = false)
    private Long hotelId;

    public HotelsDayId() {
    }

    public LocalDate getDay() {
        return daydate;
    }

    public void setDay(LocalDate day) {
        this.daydate = day;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
