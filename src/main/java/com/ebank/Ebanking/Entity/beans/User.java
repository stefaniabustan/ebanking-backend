package com.ebank.Ebanking.Entity.beans;

import com.ebank.Ebanking.Entity.enums.Status;
import com.ebank.Ebanking.Entity.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;


@Entity
//@Table(name= "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
//@Table(name="user")
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username", name = "UC_username")})
public class User implements Serializable {

    //@Id
   // @Column(name="ID")
    //@GeneratedValue
   // @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name="username", unique = true)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column
    private Status status;

    public User(String username, String password, UserType type, Status status) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
