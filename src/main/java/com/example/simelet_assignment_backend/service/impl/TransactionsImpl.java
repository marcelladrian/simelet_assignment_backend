package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.TransactionsEntity;
import com.example.simelet_assignment_backend.io.irepository.TransactionsRepository;
import com.example.simelet_assignment_backend.service.iservice.IServiceTransactions;
import com.example.simelet_assignment_backend.shared.dto.TransactionsDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import com.example.simelet_assignment_backend.ui.model.response.TransactionsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsImpl implements IServiceTransactions {

    private final TransactionsRepository transactionsRepository;
    private final GenerateRandomPublicId generateRandomPublicId;

    public TransactionsImpl(TransactionsRepository transactionsRepository, GenerateRandomPublicId generateRandomPublicId) {
        this.transactionsRepository = transactionsRepository;
        this.generateRandomPublicId = generateRandomPublicId;
    }

    //Get All Transactions
    @Override
    public List<TransactionsDTO> getAllTransactions() {
        ModelMapper mapper = new ModelMapper();

        List<TransactionsDTO> value = new ArrayList<>();
        List<TransactionsEntity> transactionsEntities = transactionsRepository.findAll();

        for (TransactionsEntity entity : transactionsEntities){
            value.add(mapper.map(entity, TransactionsDTO.class));
        }

        return value;
    }
    //Post Transactions
    @Override
    public TransactionsDTO postNewTransactions(String cardId, TransactionsDTO transactionsDTO) {
        ModelMapper mapper = new ModelMapper();


        return null;
    }


}
