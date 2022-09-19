package com.example.projectdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.lang.NonNull;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.List;

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
    private String email;
    @NonNull
    private String branch;
    @Column(unique = true)
    public String mobile;
    @Column(unique = true)
    private String panNum;
    @Column(unique = true)
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

//   @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
//   @JoinColumn(name = "Student_Id", referencedColumnName = "id")
//   private List<Address> address;

    //@Lob
    //@Type(type = "org.hibernate.type.BinaryType")
    //@Type(type = "org")
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
