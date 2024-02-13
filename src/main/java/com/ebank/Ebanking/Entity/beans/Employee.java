package com.ebank.Ebanking.Entity.beans;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;


    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    public Employee(){}

    public Employee( User userId) {

        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +

                ", userId=" + userId.toString() +
                '}';
    }
}