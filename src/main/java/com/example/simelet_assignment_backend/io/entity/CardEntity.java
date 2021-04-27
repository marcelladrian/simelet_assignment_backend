package com.example.simelet_assignment_backend.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cardTBL")
@SequenceGenerator(name = "seqCARD", initialValue = 100, allocationSize = 10)
public class CardEntity implements Serializable {
    private static final long serialVersionUID = -6638855639700802210L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCARD")
    private long id;

    @Column(nullable = false)
    private String cardid;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UsersEntity user;


    @ManyToOne
    @JoinColumn(name = "balanceid")
    private BalanceEntity balanceEntity;


//    @OneToMany(mappedBy = "cardEntity", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TransactionEntity> transactionEntity;

    @Column(nullable = false)
    private String cardimage;

    private boolean isDeleted = false;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public BalanceEntity getBalanceEntity() {
        return balanceEntity;
    }

    public void setBalanceEntity(BalanceEntity balanceEntity) {
        this.balanceEntity = balanceEntity;
    }

//    public List<TransactionEntity> getTransactionEntity() {
//        return transactionEntity;
//    }
//
//    public void setTransactionEntity(List<TransactionEntity> transactionEntity) {
//        this.transactionEntity = transactionEntity;
//    }

    public String getCardimage() {
        return cardimage;
    }

    public void setCardimage(String cardimage) {
        this.cardimage = cardimage;
    }
}
