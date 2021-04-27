package com.example.simelet_assignment_backend.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "balanceTBL")
@SequenceGenerator(name = "seqBALANCE", initialValue = 100, allocationSize = 10)
public class BalanceEntity implements Serializable {
    private static final long serialVersionUID = -4395744141871836824L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqBALANCE")
    private long id;

    @Column(nullable = false)
    private String balanceId;

    @Column(nullable = false)
    private long balance;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String barcode;

    @OneToMany(mappedBy = "balanceEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardEntity> cardEntity = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<CardEntity> getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(List<CardEntity> cardEntity) {
        this.cardEntity = cardEntity;
    }
}
