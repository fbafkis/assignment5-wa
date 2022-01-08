package com.francescobertamini.assignment5.compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class ApartmentsDayId implements Serializable {

    @Column(name = "DAYDATE", nullable = false)
    private LocalDate daydate;
    @Column(name = "APARTMENT_ID", nullable = false)
    private Long apartmentId;

    public ApartmentsDayId() {
    }

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
}
