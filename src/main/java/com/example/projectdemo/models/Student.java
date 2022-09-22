package com.example.projectdemo.models;


import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@Entity
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int id;
    @NonNull
    private String name;
    @NonNull
    //@Email
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;
    @NonNull
    private String branch;
    @Column(unique = true)
    @Pattern(regexp = "^[6-9]{1}[0-9]{9}$")
    public String mobile;
    @Column(unique = true)
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$")
    private String panNum;
    @Column(unique = true)
    @Pattern(regexp = "^[1-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$")
    private String aadharNum;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER,mappedBy = "student")
    private List<Address> addressList;


    public Student(){

    }

    public Student(int id) {
        this.id = id;
    }

    public Student(@NonNull String name, @NonNull String email, @NonNull String branch, String mobile, String panNum, String aadharNum, List<Address> address, String studentPhoto) {
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.mobile = mobile;
        this.panNum = panNum;
        this.aadharNum = aadharNum;
       // this.address = address;
        this.studentPhoto = studentPhoto;
    }

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String studentPhoto;


    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }

    public String getAadharNum() {
        return aadharNum;
    }

    public void setAadharNum(String aadharNum) {
        this.aadharNum = aadharNum;
    }

    public String getStudentPhoto() {
        return studentPhoto;
    }

    public void setStudentPhoto(String studentPhoto) {
        this.studentPhoto = studentPhoto;
    }

}
