package com.ebank.Ebanking.Entity.beans;

import com.ebank.Ebanking.Entity.enums.TransferStatus;
import com.ebank.Ebanking.Entity.enums.TypeMoneda;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "transfer")
public class Transfer implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idAccountBenef", referencedColumnName = "id")
    private Account idAccountBenef;

    @ManyToOne
    @JoinColumn(name = "idAccountDistrib", referencedColumnName = "id")
    private Account idAccountDistrib;

    @Column(name="value")
    private Float value;

    @Column(name="description")
    private String description;

    @Column(name="moneda")
    @Enumerated(EnumType.STRING)
    private TypeMoneda moneda;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getIdAccountBenef() {
        return idAccountBenef;
    }

    public void setIdAccountBenef(Account idAccountBenef) {
        this.idAccountBenef = idAccountBenef;
    }

    public Account getIdAccountDistrib() {
        return idAccountDistrib;
    }

    public void setIdAccountDistrib(Account idAccountDistrib) {
        this.idAccountDistrib = idAccountDistrib;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeMoneda getMoneda() {
        return moneda;
    }

    public void setMoneda(TypeMoneda moneda) {
        this.moneda = moneda;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }
}