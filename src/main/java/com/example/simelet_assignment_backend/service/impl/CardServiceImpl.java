package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.BalanceEntity;
import com.example.simelet_assignment_backend.io.entity.CardEntity;
import com.example.simelet_assignment_backend.io.entity.UsersEntity;
import com.example.simelet_assignment_backend.io.irepository.BalanceRepository;
import com.example.simelet_assignment_backend.io.irepository.CardRepository;
import com.example.simelet_assignment_backend.io.irepository.UsersRepository;
import com.example.simelet_assignment_backend.service.iservice.ICardInterface;
import com.example.simelet_assignment_backend.shared.dto.BalanceDTO;
import com.example.simelet_assignment_backend.shared.dto.CardDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements ICardInterface {
    private final CardRepository cardRepository;
    private final UsersRepository userRepository;
    private final BalanceRepository balanceRepository;
    private final GenerateRandomPublicId generateRandomPublicId;

    public CardServiceImpl(CardRepository cardRepository, UsersRepository userRepository, BalanceRepository balanceRepository, GenerateRandomPublicId generateRandomPublicId) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.balanceRepository = balanceRepository;
        this.generateRandomPublicId = generateRandomPublicId;
    }


    @Override
    public List<CardDTO> getAllCard(String userId) {
        List<CardDTO> value = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        UsersEntity userEntity = userRepository.findByUserId(userId);
        List<CardEntity> cards = cardRepository.findAllByUser(userEntity);

        for (CardEntity cardEntity:cards){
            value.add(mapper.map(cardEntity, CardDTO.class));
        }

        return value;
    }

    @Override
    public CardDTO getCardWithCardId(String userId, String cardId, String balanceId) {
        ModelMapper mapper = new ModelMapper();
        UsersEntity usersEntity = userRepository.findByUserId(userId);
        BalanceEntity balanceEntity = balanceRepository.findByBalanceid(balanceId);

        CardEntity cardEntity = cardRepository.findByUserAndCardidAndBalanceEntity(usersEntity, cardId, balanceEntity);
        CardDTO value = mapper.map(cardEntity, CardDTO.class);
        value.setBalanceDTO(mapper.map(cardEntity.getBalanceEntity(), BalanceDTO.class));
        return value;
    }

    @Override
    public CardDTO addNewCard(String userId, String balanceId, CardDTO cardDTO) {
        ModelMapper mapper = new ModelMapper();

        UsersEntity usersEntity = userRepository.findByUserId(userId);
        BalanceEntity balanceEntity = balanceRepository.findByBalanceid(balanceId);

        CardEntity cardEntity = mapper.map(cardDTO, CardEntity.class);
        cardEntity.setBalanceEntity(balanceEntity);
        cardEntity.setUser(usersEntity);
        cardEntity.setCardid(generateRandomPublicId.generateUserId(30));

        CardEntity createdValue = cardRepository.save(cardEntity);
        return mapper.map(createdValue, CardDTO.class);
    }

    @Override
    public CardDTO updateCard(String cardId, CardDTO cardDTO) {
        ModelMapper mapper = new ModelMapper();

        CardEntity entity = cardRepository.findByCardid(cardId);
        entity.setName(cardDTO.getName());
        entity.setCardimage(cardDTO.getCardimage());

        CardEntity updatedValue = cardRepository.save(entity);
        return mapper.map(updatedValue, CardDTO.class);
    }

    @Override
    public CardDTO deleteCard(String cardId) {
        ModelMapper mapper = new ModelMapper();

        CardEntity entity = cardRepository.findByCardid(cardId);
        entity.setDeleted(true);
        CardEntity deletedCard = cardRepository.save(entity);
        return mapper.map(deletedCard, CardDTO.class);
    }
}
