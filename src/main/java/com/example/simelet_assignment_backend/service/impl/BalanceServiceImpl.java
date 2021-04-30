package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.BalanceEntity;
import com.example.simelet_assignment_backend.io.irepository.BalanceRepository;
import com.example.simelet_assignment_backend.service.iservice.IBalanceInterface;
import com.example.simelet_assignment_backend.shared.dto.BalanceDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import com.example.simelet_assignment_backend.shared.utils.HashingPassword;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BalanceServiceImpl implements IBalanceInterface {
    private final BalanceRepository balanceRepository;
    private final GenerateRandomPublicId generateRandomPublicId;
    private final HashingPassword hashingPassword;

    public BalanceServiceImpl(BalanceRepository balanceRepository, GenerateRandomPublicId generateRandomPublicId, HashingPassword hashingPassword) {
        this.balanceRepository = balanceRepository;
        this.generateRandomPublicId = generateRandomPublicId;
        this.hashingPassword = hashingPassword;
    }


    @Override
    public BalanceDTO addNewBalance(BalanceDTO balanceDTO) {
        ModelMapper mapper = new ModelMapper();
        BalanceEntity balanceEntity = mapper.map(balanceDTO, BalanceEntity.class);

        balanceEntity.setBalanceid(generateRandomPublicId.generateUserId(30));
        balanceEntity.setCreatedAt(LocalDateTime.now());

        String encodedPassword = hashingPassword.generateHashPassword(balanceEntity.getPassword());
        balanceEntity.setPassword(encodedPassword);

        BalanceEntity createdValue = balanceRepository.save(balanceEntity);
        return mapper.map(createdValue, BalanceDTO.class);
    }

    @Override
    public BalanceDTO updateBalance(String balanceId, BalanceDTO balanceDTO) {
        ModelMapper mapper = new ModelMapper();
        BalanceEntity balanceEntity = balanceRepository.findByBalanceid(balanceId);

        balanceEntity.setBalance(balanceDTO.getBalance());
        balanceEntity.setUpdateAt(LocalDateTime.now());

        String encodedPassword = hashingPassword.generateHashPassword(balanceDTO.getPassword());
        balanceEntity.setPassword(encodedPassword);

        BalanceEntity updatedValue = balanceRepository.save(balanceEntity);
        return mapper.map(updatedValue, BalanceDTO.class);
    }

    @Override
    public BalanceDTO getBalanceByBalanceId(String balanceId) {
        ModelMapper mapper = new ModelMapper();
        BalanceEntity balanceEntity = balanceRepository.findByBalanceid(balanceId);

        return mapper.map(balanceEntity, BalanceDTO.class);
    }
}
