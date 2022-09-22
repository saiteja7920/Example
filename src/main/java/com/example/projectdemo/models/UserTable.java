package com.example.projectdemo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @NonNull
    private String name;
    @NonNull
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;
    @NonNull
    @Column(unique = true)
    private String userName;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$")
    private String password;
    @CreatedDate
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdOn;
    @LastModifiedDate
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedOn;
    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    protected void prePersist() {
        if (this.createdOn == null) createdOn = new Date();
        if (this.updatedOn == null) updatedOn = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedOn = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updatedOn = new Date();
    }

    public UserTable(int id, @NonNull String name, @NonNull String email, @NonNull String userName, String password, Date createdOn, Date updatedOn, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.status = status;
    }

    public UserTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
