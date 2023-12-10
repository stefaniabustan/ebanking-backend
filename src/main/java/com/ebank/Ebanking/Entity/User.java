package com.ebank.Ebanking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name= "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

}
