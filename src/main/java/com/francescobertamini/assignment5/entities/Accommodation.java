package com.francescobertamini.assignment5.entities;

import javax.persistence.*;

@Entity
@Table(name = "ACCOMMODATIONS")

public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "NAME", nullable = false, length = 100)
    public String name;
    @Basic
    @Column(name = "PRICE", nullable = false)
    public int price;
    @Basic
    @Column(name = "TYPE", nullable = false)
    public int type;

    @OneToOne(mappedBy = "accommodation", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Apartment apartment;
    @OneToOne(mappedBy = "accommodation", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Hotel hotel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
