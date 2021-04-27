package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.CardEntity;
import com.example.simelet_assignment_backend.io.entity.TransactionsEntity;
import com.example.simelet_assignment_backend.io.entity.UsersEntity;
import com.example.simelet_assignment_backend.io.irepository.CardRepository;
import com.example.simelet_assignment_backend.io.irepository.TransactionsRepository;
import com.example.simelet_assignment_backend.io.irepository.UsersRepository;
import com.example.simelet_assignment_backend.service.iservice.IServiceTransactions;
import com.example.simelet_assignment_backend.shared.dto.TransactionsDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsImpl implements IServiceTransactions {

    private final TransactionsRepository transactionsRepository;
    private final CardRepository cardRepository;
    private final GenerateRandomPublicId generateRandomPublicId;
    private final UsersRepository usersRepository;

    public TransactionsImpl(TransactionsRepository transactionsRepository, CardRepository cardRepository, GenerateRandomPublicId generateRandomPublicId, UsersRepository usersRepository) {
        this.transactionsRepository = transactionsRepository;
        this.cardRepository = cardRepository;
        this.generateRandomPublicId = generateRandomPublicId;
        this.usersRepository = usersRepository;
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
    public TransactionsDTO postNewTransactions(String cardid, TransactionsDTO transactionsDTO) {
        ModelMapper mapper = new ModelMapper();

        CardEntity cardEntity = cardRepository.findByCardid(cardid);

        TransactionsEntity entity = mapper.map(transactionsDTO, TransactionsEntity.class);
        entity.setCardEntity(cardEntity);
        entity.setTransactionsId(generateRandomPublicId.generateUserId(35));

        TransactionsEntity storedValue = transactionsRepository.save(entity);
        TransactionsDTO value = mapper.map(storedValue, TransactionsDTO.class);

        return value;
    }
    //Get Transactions by card id
    @Override
    public List<TransactionsDTO> getAllTransactionsByCardId(String cardid) {
        ModelMapper mapper = new ModelMapper();
        CardEntity cardEntity = cardRepository.findByCardid(cardid);

        List<TransactionsDTO> value = new ArrayList<>();
        List<TransactionsEntity> transactionsEntities = transactionsRepository.findAllByCardEntity(cardEntity);

        for (TransactionsEntity entity : transactionsEntities){
            value.add(mapper.map(entity, TransactionsDTO.class));
        }

        return value;
    }
    //delete transactions
    @Override
    public TransactionsDTO deleteTransactions(String cardid, String transactionsid) {
        ModelMapper mapper = new ModelMapper();

        CardEntity cardEntity = cardRepository.findByCardid(cardid);
        TransactionsEntity transactionsEntity = transactionsRepository.findByTransactionsId(transactionsid);

        transactionsEntity.setCardEntity(cardEntity);
        transactionsEntity.setDeleted(true);

        TransactionsEntity storedValue = transactionsRepository.save(transactionsEntity);

        return mapper.map(storedValue, TransactionsDTO.class);
    }
    //get trasnsactions by user id
//    @Override
//    public List<TransactionsDTO> getAllTransactionsByUserId(String userid) {
//        ModelMapper mapper = new ModelMapper();
//
//        UsersEntity usersEntity = usersRepository.findByUserId(userid);
//
//        List<TransactionsDTO> value = new ArrayList<>();
//        List<TransactionsEntity> transactionsEntities = transactionsRepository.findAllByUsersEntity(usersEntity);
//
//        for (TransactionsEntity entity : transactionsEntities){
//            value.add(mapper.map(entity, TransactionsDTO.class));
//        }
//        return value;
//    }


}
