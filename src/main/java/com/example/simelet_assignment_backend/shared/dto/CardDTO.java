package com.example.simelet_assignment_backend.shared.dto;

import java.io.Serializable;

public class CardDTO implements Serializable {
    private static final long serialVersionUID = -1731146540324119309L;
    private long id;
    private String cardId;
    private String name;
    private BalanceDTO balanceDTO;
    private String cardImage;
    private boolean isDeleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BalanceDTO getBalanceDTO() {
        return balanceDTO;
    }

    public void setBalanceDTO(BalanceDTO balanceDTO) {
        this.balanceDTO = balanceDTO;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
