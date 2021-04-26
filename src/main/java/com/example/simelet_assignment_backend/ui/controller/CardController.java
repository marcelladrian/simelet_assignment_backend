package com.example.simelet_assignment_backend.ui.controller;

import com.example.simelet_assignment_backend.service.iservice.ICardInterface;
import com.example.simelet_assignment_backend.shared.dto.CardDTO;
import com.example.simelet_assignment_backend.ui.model.request.CardRequest;
import com.example.simelet_assignment_backend.ui.model.response.CardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    @Autowired
    ICardInterface iCardInterface;

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CardResponse> getAllCards(@PathVariable String userId){
        List<CardResponse> value = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        List<CardDTO> cards = iCardInterface.getAllCard(userId);

        for (CardDTO cardDTO : cards){
            value.add(mapper.map(cardDTO, CardResponse.class));
        }
        return value;
    }

    @GetMapping(path = "/{userId}/{cardId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CardResponse getCardWithCardId(@PathVariable String cardId){
        ModelMapper mapper = new ModelMapper();

        CardDTO cardDTO = iCardInterface.getCardWithCardId(cardId);

        return mapper.map(cardDTO, CardResponse.class);
    }

    @PostMapping(path = "/{userId}/{balanceId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CardResponse addNewCard(@PathVariable String userId, @PathVariable String balanceId, @RequestBody CardRequest cardRequest){
        ModelMapper mapper = new ModelMapper();

        CardDTO cardDTO = mapper.map(cardRequest, CardDTO.class);

        CardDTO createdCard = iCardInterface.addNewCard(userId, balanceId, cardDTO);

        return mapper.map(createdCard, CardResponse.class);
    }

    @PutMapping(path = "/{cardId}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CardResponse updateCardName(@PathVariable String cardId, @RequestBody CardRequest cardRequest){
        ModelMapper mapper = new ModelMapper();

        CardDTO cardDTO = mapper.map(cardRequest, CardDTO.class);

        CardDTO updatedCard = iCardInterface.updateCard(cardId, cardDTO);

        return mapper.map(updatedCard, CardResponse.class);
    }

    @DeleteMapping(path = "/{cardId}",
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public CardResponse deleteCard(@PathVariable String cardId){
        ModelMapper mapper = new ModelMapper();

        CardDTO deletedCard = iCardInterface.deleteCard(cardId);

        return mapper.map(deletedCard, CardResponse.class);
    }
}
