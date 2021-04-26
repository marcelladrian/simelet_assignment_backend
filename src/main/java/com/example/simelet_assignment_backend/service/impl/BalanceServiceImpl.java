package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.BalanceEntity;
import com.example.simelet_assignment_backend.io.irepository.BalanceRepository;
import com.example.simelet_assignment_backend.service.iservice.IBalanceInterface;
import com.example.simelet_assignment_backend.shared.dto.BalanceDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements IBalanceInterface {
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    GenerateRandomPublicId generateRandomPublicId;


    @Override
    public BalanceDTO addNewBalance(BalanceDTO balanceDTO) {
        ModelMapper mapper = new ModelMapper();
        BalanceEntity balanceEntity = mapper.map(balanceDTO, BalanceEntity.class);

        balanceEntity.setBalanceId(generateRandomPublicId.generateUserId(30));
        BalanceEntity createdValue = balanceRepository.save(balanceEntity);
        return mapper.map(createdValue, BalanceDTO.class);
    }

    @Override
    public BalanceDTO updateBalance(String balanceId, BalanceDTO balanceDTO) {
        ModelMapper mapper = new ModelMapper();
        BalanceEntity balanceEntity = balanceRepository.findByBalanceId(balanceId);

        balanceEntity.setBalance(balanceDTO.getBalance());
        balanceEntity.setPassword(balanceDTO.getPassword());

        BalanceEntity updatedValue = balanceRepository.save(balanceEntity);
        return mapper.map(updatedValue, BalanceDTO.class);
    }

    @Override
    public BalanceDTO getBalanceByBalanceId(String balanceId) {
        ModelMapper mapper = new ModelMapper();
        BalanceEntity balanceEntity = balanceRepository.findByBalanceId(balanceId);

        return mapper.map(balanceEntity, BalanceDTO.class);
    }
}
