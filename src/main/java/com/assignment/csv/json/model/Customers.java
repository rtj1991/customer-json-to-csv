package com.assignment.csv.json.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "customers")
public class Customers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "_id")
    private String id;

    @Column(name = "index")
    private String index;

    @Column(name = "age")
    private String age;

    @Column(name = "eyeColor")
    private String eyeColor;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "company")
    private String company;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "number")
    private String number;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "about")
    private String about;

    @Column(name = "registered")
    private String registered;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "tags")
    private String tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers that = (Customers) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}