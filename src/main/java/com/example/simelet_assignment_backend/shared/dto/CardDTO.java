package com.example.simelet_assignment_backend.shared.dto;

import org.apache.catalina.User;

import java.io.Serializable;

public class CardDTO implements Serializable {
    private static final long serialVersionUID = -1731146540324119309L;
    private long id;
    private String cardid;
    private String name;
    private UsersDTO usersDTO;
    private BalanceDTO balanceDTO;
    private String cardimage;
    private boolean isDeleted;

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

    public UsersDTO getUsersDTO() {
        return usersDTO;
    }

    public void setUsersDTO(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

    public BalanceDTO getBalanceDTO() {
        return balanceDTO;
    }

    public void setBalanceDTO(BalanceDTO balanceDTO) {
        this.balanceDTO = balanceDTO;
    }

    public String getCardimage() {
        return cardimage;
    }

    public void setCardimage(String cardimage) {
        this.cardimage = cardimage;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
