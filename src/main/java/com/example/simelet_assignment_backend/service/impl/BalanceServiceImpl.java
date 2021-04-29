package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.BalanceEntity;
import com.example.simelet_assignment_backend.io.irepository.BalanceRepository;
import com.example.simelet_assignment_backend.service.iservice.IBalanceInterface;
import com.example.simelet_assignment_backend.shared.dto.BalanceDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BalanceServiceImpl implements IBalanceInterface {
    private final BalanceRepository balanceRepository;
    private final GenerateRandomPublicId generateRandomPublicId;

    String pepper = "pepper";
    int iterations = 200000;
    int hashWidth = 256;
    private final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth);;

    public BalanceServiceImpl(BalanceRepository balanceRepository, GenerateRandomPublicId generateRandomPublicId) {
        this.balanceRepository = balanceRepository;
        this.generateRandomPublicId = generateRandomPublicId;
    }


    @Override
    public BalanceDTO addNewBalance(BalanceDTO balanceDTO) {
        ModelMapper mapper = new ModelMapper();
        BalanceEntity balanceEntity = mapper.map(balanceDTO, BalanceEntity.class);

        balanceEntity.setBalanceid(generateRandomPublicId.generateUserId(30));
        balanceEntity.setCreatedAt(LocalDateTime.now());

        pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = pbkdf2PasswordEncoder.encode(balanceEntity.getPassword());
        balanceEntity.setPassword(encodedPassword);

        BalanceEntity createdValue = balanceRepository.save(balanceEntity);
        return mapper.map(createdValue, BalanceDTO.class);
    }

    @Override
    public BalanceDTO updateBalance(String balanceId, BalanceDTO balanceDTO) {
        ModelMapper mapper = new ModelMapper();
        BalanceEntity balanceEntity = balanceRepository.findByBalanceid(balanceId);

        balanceEntity.setBalance(balanceDTO.getBalance());

        pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = pbkdf2PasswordEncoder.encode(balanceEntity.getPassword());
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
