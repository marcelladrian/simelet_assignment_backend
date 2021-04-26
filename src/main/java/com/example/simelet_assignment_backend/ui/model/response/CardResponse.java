package com.example.simelet_assignment_backend.ui.model.response;

import com.example.simelet_assignment_backend.shared.dto.BalanceDTO;

public class CardResponse {
    private String cardId;
    private String name;
    private String cardImage;
    private BalanceDTO balanceDTO;
    private boolean isDeleted;

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

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public BalanceDTO getBalanceDTO() {
        return balanceDTO;
    }

    public void setBalanceDTO(BalanceDTO balanceDTO) {
        this.balanceDTO = balanceDTO;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
