package com.example.simelet_assignment_backend.service.iservice;

import com.example.simelet_assignment_backend.shared.dto.BalanceDTO;

public interface IBalanceInterface {
    BalanceDTO addNewBalance(BalanceDTO balanceDTO);

    BalanceDTO updateBalance(String balanceId, BalanceDTO balanceDTO);

    BalanceDTO getBalanceByBalanceId(String balanceId);
}
