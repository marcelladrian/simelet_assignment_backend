package com.example.simelet_assignment_backend.service.iservice;

import com.example.simelet_assignment_backend.shared.dto.TransactionsDTO;

import java.util.List;

public interface IServiceTransactions {

    List<TransactionsDTO> getAllTransactions();

    TransactionsDTO postNewTransactions(String cardId, TransactionsDTO transactionsDTO);
}
