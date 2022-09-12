package com.example.projectdemo.models;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Street;
    private String City;
    private String State;
    private String Country;
    private String Pincode;

    public Address(){

    }

    public Address(String street, String city, String state, String country, String pincode) {
        Street = street;
        City = city;
        State = state;
        Country = country;
        Pincode = pincode;
    }

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "Student_Id")
    private Student student;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = Id;
    }

    @NonNull
    public String getStreet() {
        return Street;
    }

    public void setStreet(@NonNull String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
