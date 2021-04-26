package com.example.simelet_assignment_backend.service.impl;

import com.example.simelet_assignment_backend.io.entity.BalanceEntity;
import com.example.simelet_assignment_backend.io.entity.CardEntity;
import com.example.simelet_assignment_backend.io.irepository.BalanceRepository;
import com.example.simelet_assignment_backend.io.irepository.CardRepository;
import com.example.simelet_assignment_backend.service.iservice.ICardInterface;
import com.example.simelet_assignment_backend.shared.dto.CardDTO;
import com.example.simelet_assignment_backend.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements ICardInterface {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    GenerateRandomPublicId generateRandomPublicId;

    @Override
    public List<CardDTO> getAllCard(String userId) {
        List<CardDTO> value = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserid(userId);
        List<CardEntity> cards = cardRepository.findAllByUser(userEntity);

        for (CardEntity cardEntity:cards){
            value.add(mapper.map(cardEntity, CardDTO.class));
        }

        return value;
    }

    @Override
    public CardDTO getCardWithCardId(String cardId) {
        ModelMapper mapper = new ModelMapper();

        CardEntity cardEntity = cardRepository.findByCardid(cardId);
        return mapper.map(cardEntity, CardDTO.class);
    }

    @Override
    public CardDTO addNewCard(String balanceId, CardDTO cardDTO) {
        ModelMapper mapper = new ModelMapper();

        BalanceEntity balanceEntity = balanceRepository.findByBalanceid(balanceId);

        CardEntity cardEntity = mapper.map(cardDTO, CardEntity.class);
        cardEntity.setBalanceEntity(balanceEntity);
        cardEntity.setCardid(generateRandomPublicId.generateUserId(30));

        CardEntity createdValue = cardRepository.save(cardEntity);
        return mapper.map(createdValue, CardDTO.class);
    }

    @Override
    public CardDTO updateCard(String cardId, CardDTO cardDTO) {
        ModelMapper mapper = new ModelMapper();

        CardEntity entity = cardRepository.findByCardid(cardId);
        entity.setName(cardDTO.getName());
        entity.setCardimage(cardDTO.getCardImage());

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
