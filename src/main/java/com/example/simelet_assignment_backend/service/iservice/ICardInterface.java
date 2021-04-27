package com.example.simelet_assignment_backend.service.iservice;

import com.example.simelet_assignment_backend.shared.dto.CardDTO;

import java.util.List;

public interface ICardInterface {
    List<CardDTO> getAllCard(String userId);

    CardDTO getCardWithCardId(String cardId);

    CardDTO addNewCard(String userId, String balanceId, CardDTO cardDTO);

    CardDTO updateCard(String cardId, CardDTO cardDTO);

    CardDTO deleteCard(String cardId);
}
