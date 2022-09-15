package com.example.projectdemo.models;

import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String branch;
    @Column(unique = true)
    public String mobile_num;
    @Column(unique = true)
    private String pan_num;
    @Column(unique = true)
    private String Aadhar_num;

    public Student(){

    }

    public Student(int id) {
        this.id = id;
    }

    public Student(@NonNull String name, @NonNull String email, @NonNull String branch, String mobile_num, String pan_num, String aadhar_num, Address address, byte[] student_photo) {
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.mobile_num = mobile_num;
        this.pan_num = pan_num;
        Aadhar_num = aadhar_num;
        //this.address = address;
        Student_photo = student_photo;
    }

   // @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "student")
    //private Address address;
   @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
   @JoinColumn(name = "student_id", referencedColumnName = "id")
   private List<Address> addresses;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] Student_photo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

    public String getPan_num() {
        return pan_num;
    }

    public void setPan_num(String pan_num) {
        this.pan_num = pan_num;
    }

    public String getAadhar_num() {
        return Aadhar_num;
    }

    public void setAadhar_num(String aadhar_num) {
        Aadhar_num = aadhar_num;
    }

    public byte[] getStudent_photo() {
        return Student_photo;
    }

    public void setStudent_photo(byte[] student_photo) {
        Student_photo = student_photo;
    }

}
