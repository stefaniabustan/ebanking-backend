package com.ebank.Ebanking.Entity.beans;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column
    private String firstname;

    @Column
    private String secondname;

    @Column
    private String address;

    @Column
    private String email;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;



    public Client(){}

    public Client(String firstname, String secondname, String address, String email, User userId) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.address = address;
        this.email = email;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }
}
