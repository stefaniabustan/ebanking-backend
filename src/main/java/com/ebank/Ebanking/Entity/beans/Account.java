package com.ebank.Ebanking.Entity.beans;

import com.ebank.Ebanking.Entity.enums.TypeMoneda;
import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(columnNames = "number", name = "UC_number")})
public class Account implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    private Client clientId;

    @Column(name="number", unique = true)
    private String number;

    @Column(name="balance")
    private Float balance;

    @Column(name="description")
    private String description;

    @Column(name="moneda")
    @Enumerated(EnumType.STRING)
    private TypeMoneda moneda;

    public Account( Client clientId, String number, Float balance, String description, TypeMoneda moneda) {
        this.clientId = clientId;
        this.number = number;
        this.balance = balance;
        this.description = description;
        this.moneda = moneda;
    }

    public Account() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public TypeMoneda getMoneda() {
        return moneda;
    }

    public void setMoneda(TypeMoneda moneda) {
        this.moneda = moneda;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Float getBalance() {
//        if(balance==null)
//            return Float.valueOf(0);
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
